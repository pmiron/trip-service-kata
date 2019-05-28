package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserBuilder
import org.junit.Assert.assertEquals
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

    }

   @Before
   fun initialize(){
       tripService = TestableTripService()
   }

    @Test(expected = UserNotLoggedInException::class)
    fun should_throw_an_exception_when_user_is_not_logged_in() {
        tripService!!.getTripsByUser(UNUSED_USER, GUEST)
    }

    @Test
    fun should_not_return_any_trips_when_users_are_not_friends() {
        var friend = UserBuilder.aUser()
                .withFriends(ANOTHER_USER)
                .withTrips(TO_BRAZIL)
                .build()

        val tripsByUser = tripService!!.getTripsByUser(friend, REGISTERD_USER)

        assertEquals(tripsByUser.size, 0)
    }

    @Test
    fun should_return_friend_trips_then_users_are_friends() {
        var friend = UserBuilder.aUser()
                .withFriends(ANOTHER_USER, REGISTERD_USER)
                .withTrips(TO_BRAZIL, TO_LONDON)
                .build()
        val tripsByUser = tripService!!.getTripsByUser(friend, REGISTERD_USER)

        assertEquals(tripsByUser.size, 2)
    }

    class TestableTripService : TripService(){
        override fun tripsBy(user: User): List<Trip> {
            return user.trips
        }
    }
}
