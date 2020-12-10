package net.altunyay.myunittestapp.ui.login.model

import net.altunyay.myunittestapp.ui.login.model.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)