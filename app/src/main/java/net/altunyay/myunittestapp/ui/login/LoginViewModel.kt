package net.altunyay.myunittestapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.altunyay.myunittestapp.R
import net.altunyay.myunittestapp.data.LoginRepository
import net.altunyay.myunittestapp.data.Result
import net.altunyay.myunittestapp.ui.login.mapper.toLoggedInUserView
import net.altunyay.myunittestapp.ui.login.model.LoginResult
import net.altunyay.myunittestapp.ui.login.model.LoginViewState
import net.altunyay.myunittestapp.utils.isEmailValid
import net.altunyay.myunittestapp.utils.isPasswordValid


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    //view state
    private val _loginViewState = MutableLiveData(LoginViewState())
    val loginViewState: LiveData<LoginViewState> = _loginViewState

    //action state
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    //inputs
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkDataThenLogin() {
        if(loginViewState.value?.getDataValid() == true) {
            login()
        }
    }

    fun login() {

        _loginViewState.value = _loginViewState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = loginRepository.login(email.value, password.value)

            if (result is Result.Success) {
                _loginResult.value = LoginResult(success = result.data.toLoggedInUserView())
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }

        }.invokeOnCompletion {
            _loginViewState.value = _loginViewState.value?.copy(isLoading = false)
        }

    }

    fun emailDataChanged(email: String?) {

        val error = email?.takeIf { !it.isEmailValid() }?.let { R.string.invalid_email }
        val loginViewState = _loginViewState.value ?: LoginViewState()

        _loginViewState.value = loginViewState.copy(emailError = error)
    }

    fun passwordDataChanged(password: String?) {

        val error = password?.takeIf { !it.isPasswordValid() }?.let { R.string.invalid_password }
        val loginViewState = _loginViewState.value ?: LoginViewState()

        _loginViewState.value = loginViewState.copy(passwordError = error)
    }
}

