package org.craftedsw.tripservicekata.user

import org.craftedsw.tripservicekata.trip.Trip
import java.util.ArrayList

class User {

    val trips: MutableList<Trip> = ArrayList()

    val friends: MutableList<User> = ArrayList()

    fun addFriend(user: User) = friends.add(user)

    fun addTrip(trip: Trip) = trips.add(trip)

    fun isFriendsWith(anotherUser: User): Boolean {
        return friends.contains(anotherUser)
    }

}
