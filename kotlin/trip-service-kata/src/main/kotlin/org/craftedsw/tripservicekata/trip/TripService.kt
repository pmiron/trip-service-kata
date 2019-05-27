package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User

open class TripService {

    fun getTripsByUser(user: User?, loggedInUser: User?): List<Trip> {
        loggedInUser ?: throw UserNotLoggedInException()
        return if (user!!.isFriendsWith(loggedInUser)) tripsBy(user) else noTrips()
    }

    protected open fun tripsBy(user: User) = TripDAO.findTripsByUser(user)

    private fun noTrips(): List<Trip> = emptyList()


}
