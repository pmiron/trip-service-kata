package org.craftedsw.tripservicekata

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.trip.Trip
import org.craftedsw.tripservicekata.trip.TripDAO
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession

class TripService_Original {

    fun getTripsByUser(user: User): List<Trip> {
        val loggedInUser: User = UserSession.instance.loggedUser ?: throw UserNotLoggedInException()
        return if (user.isFriendsWith(loggedInUser)) tripsBy(user) else noTrips()
    }

    private fun tripsBy(user: User) = TripDAO.findTripsByUser(user)

    private fun noTrips(): List<Trip> = emptyList()

}
