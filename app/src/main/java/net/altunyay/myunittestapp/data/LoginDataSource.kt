package net.altunyay.myunittestapp.data

import net.altunyay.myunittestapp.data.model.LoggedInUser
import java.io.IOException
import java.lang.NullPointerException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(email: String?, password: String?): Result<LoggedInUser> {
        return try {
            email ?: throw NullPointerException()
            password ?: throw NullPointerException()
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), email)
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}