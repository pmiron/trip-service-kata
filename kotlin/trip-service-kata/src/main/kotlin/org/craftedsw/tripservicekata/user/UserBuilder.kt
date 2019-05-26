package org.craftedsw.tripservicekata.user

import org.craftedsw.tripservicekata.trip.Trip

class UserBuilder {
    private lateinit var trips: Array<out Trip>
    private lateinit var friends: Array<out User>

    fun withFriends(vararg friends: User): UserBuilder {
        this.friends = friends
        return this
    }

    fun withTrips(vararg trips: Trip): UserBuilder {
        this.trips = trips
        return this
    }

    fun build(): User {
        var user = User()
        addFriendsTo(user)
        addTripsTo(user)

        return user
    }

    private fun addTripsTo(user: User) {
        for (trip in trips) {
            user.addTrip(trip)
        }

    }

    private fun addFriendsTo(user: User) {
        for (friend in friends) {
            user.addFriend(friend)
        }
    }

    companion object {
        fun aUser(): UserBuilder {
            return UserBuilder()
        }
    }
}
