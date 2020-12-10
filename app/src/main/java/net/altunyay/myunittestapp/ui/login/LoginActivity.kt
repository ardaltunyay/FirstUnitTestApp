package net.altunyay.myunittestapp.ui.login

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import net.altunyay.myunittestapp.R
import net.altunyay.myunittestapp.databinding.ActivityLoginBinding
import net.altunyay.myunittestapp.ui.login.model.LoggedInUserView


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
    }
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        loginViewModel.email.observe(this) { loginViewModel.emailDataChanged(it) }

        loginViewModel.password.observe(this) { loginViewModel.passwordDataChanged(it) }

        loginViewModel.loginResult.observe(this) { loginResult ->
            when {
                loginResult.success != null -> {
                    updateUiWithUser(loginResult.success)
                }
                loginResult.error != null -> {
                    showLoginFailed(loginResult.error)
                }
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Snackbar.make(
            binding.root,
            "$welcome $displayName",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Error")
            setMessage(errorString)
            setPositiveButton(R.string.ok, null)
        }.show()
    }
}