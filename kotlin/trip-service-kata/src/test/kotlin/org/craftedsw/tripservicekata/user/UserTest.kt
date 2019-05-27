package org.craftedsw.tripservicekata.user

import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun should_inform_when_users_are_not_friends() {
        val BOB = User()
        val PAUL = User()

        var user = UserBuilder.aUser().withFriends(BOB)
                .build();
        assertEquals(user.isFriendsWith(PAUL), false)
    }

    @Test
    fun should_inform_when_users_are_friends() {
        val BOB = User()
        val PAUL = User()

        var user = UserBuilder.aUser().withFriends(BOB, PAUL)
                .build();
        assertEquals(user.isFriendsWith(PAUL), true)
    }
}
