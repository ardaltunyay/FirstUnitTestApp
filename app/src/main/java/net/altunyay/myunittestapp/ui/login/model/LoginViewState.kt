package net.altunyay.myunittestapp.ui.login.model

import androidx.annotation.StringRes
import net.altunyay.myunittestapp.R

/**
 * Data validation state of the login form.
 */
data class LoginViewState(
    @StringRes val emailError: Int? = R.string.empty,
    @StringRes val passwordError: Int? = R.string.empty,
    val isLoading: Boolean = false
) {
    fun getDataValid(): Boolean = (!isLoading && emailError == null && passwordError == null)
}

