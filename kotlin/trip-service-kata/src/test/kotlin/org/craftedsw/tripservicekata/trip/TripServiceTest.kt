package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class TripServiceTest {

    companion object {
        var GUEST = null
        var REGISTERD_USER = User()
        var UNUSED_USER = null
        var ANOTHER_USER = User()
        var TO_BRAZIL = Trip()
        var TO_LONDON = Trip()
        var loggedInUser:User? = null
    }

    @Test(expected = UserNotLoggedInException::class)
    fun should_throw_an_exception_when_user_is_not_logged_in() {
        loggedInUser = GUEST
        var tripService = TestableTripService()
        tripService.getTripsByUser(UNUSED_USER)
    }

    @Test
    fun should_not_return_any_trips_when_users_are_not_friends(){
        loggedInUser = REGISTERD_USER

        var friend = User()
        friend.addFriend(ANOTHER_USER)
        friend.addTrip(TO_BRAZIL)
        var tripService = TestableTripService()
        val tripsByUser = tripService.getTripsByUser(friend)

        assertEquals(tripsByUser.size, 0)
    }

    @Test
    fun should_return_friend_trips_then_users_are_friends(){
        loggedInUser = REGISTERD_USER

        var friend = User()
        friend.addFriend(ANOTHER_USER)
        friend.addFriend(loggedInUser!!)
        friend.addTrip(TO_BRAZIL)
        friend.addTrip(TO_LONDON)
        var tripService = TestableTripService()
        val tripsByUser = tripService.getTripsByUser(friend)

        assertEquals(tripsByUser.size, 2)
    }

    class TestableTripService : TripService(){
        protected override fun getLoggedUser() = loggedInUser

        override fun tripsBy(user: User?): List<Trip> {
            return user!!.trips
        }
    }
}
