package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class TripServiceTest {

    var tripService: TripService? = null

    companion object {
        var GUEST = null
        var REGISTERD_USER = User()
        var UNUSED_USER = null
        var ANOTHER_USER = User()
        var TO_BRAZIL = Trip()
        var TO_LONDON = Trip()
        var loggedInUser:User? = null

    }

   @Before
   fun initialize(){
       tripService = TestableTripService()
       loggedInUser = REGISTERD_USER
   }

    @Test(expected = UserNotLoggedInException::class)
    fun should_throw_an_exception_when_user_is_not_logged_in() {
        loggedInUser = GUEST
        tripService!!.getTripsByUser(UNUSED_USER)
    }

    @Test
    fun should_not_return_any_trips_when_users_are_not_friends(){
        var friend = User()
        friend.addFriend(ANOTHER_USER)
        friend.addTrip(TO_BRAZIL)
        val tripsByUser = tripService!!.getTripsByUser(friend)

        assertEquals(tripsByUser.size, 0)
    }

    @Test
    fun should_return_friend_trips_then_users_are_friends(){
        var friend = User()
        friend.addFriend(ANOTHER_USER)
        friend.addFriend(loggedInUser!!)
        friend.addTrip(TO_BRAZIL)
        friend.addTrip(TO_LONDON)
        val tripsByUser = tripService!!.getTripsByUser(friend)

        assertEquals(tripsByUser.size, 2)
    }

    class TestableTripService : TripService(){
        override fun getLoggedUser() = loggedInUser

        override fun tripsBy(user: User?): List<Trip> {
            return user!!.trips
        }
    }
}
