package com.technource.android.ui.loginModule

import android.content.Intent
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityLoginBinding
import com.technource.android.base.BaseActivity
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.dashboardModule.DashboardActivity
import com.technource.android.ui.forgotPasswordModule.ForgotPasswordActivity
import com.technource.android.ui.registrationModule.SignupActivity
import com.technource.android.utils.ValidationStatus
import com.technource.android.utils.getErrorMessage
import com.technource.android.utils.validateEmail
import com.technource.android.utils.validatePassword

class LoginActivity : BaseActivity<ActivityLoginBinding>(), LoginNavigator {
    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)
    private lateinit var viewModel: LoginViewModel
    private lateinit var preference: PreferencesHelperImpl
    override fun initObj() {
        // Initialize PreferencesHelperImpl instance
        preference = PreferencesHelperImpl(this)

        // Initialize LoginViewModel instance
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Set viewModel and lifecycle owner for data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Set the navigator for viewModel
        viewModel.setNavigator(this)

        // Observe the email validation status
        viewModel.emailValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the password validation status
        viewModel.passwordValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.passwordTextInput.error = errorMessage
            binding.passwordTextInput.isErrorEnabled = errorMessage != null
        }

        // Add a text changed listener to the email EditText
        binding.emailET.addTextChangedListener {
            val email = it.toString().trim()
            val emailStatus = validateEmail(email)
            viewModel.emailValidationStatus.value = emailStatus
        }

        // Add a text changed listener to the password EditText
        binding.passwordET.addTextChangedListener {
            val password = it.toString().trim()
            val passwordStatus = validatePassword(password)
            viewModel.passwordValidationStatus.value = passwordStatus
        }

    }

    override fun login() {
        val email: String = binding.emailET.text.toString()
        val password: String = binding.passwordET.text.toString()

        // Validate email and password
        val emailStatus = validateEmail(email)
        val passwordStatus = validatePassword(password)

        if (emailStatus == ValidationStatus.VALID && passwordStatus == ValidationStatus.VALID) {
            // Set isLoggedIn to true in preferences
            preference.setIsLoggedIn(true)
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            finishAffinity()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
        if (emailStatus != ValidationStatus.VALID) {
            // Display email validation error message
            val errorMessage = emailStatus.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        }
        if (passwordStatus == ValidationStatus.EMPTY_PASSWORD || passwordStatus == ValidationStatus.INVALID_PASSWORD) {
            // Display password validation error message
            val errorMessage = passwordStatus.getErrorMessage(this)
            binding.passwordTextInput.error = errorMessage
            binding.passwordTextInput.isErrorEnabled = errorMessage != null
        }
    }

    override fun click() {
        binding.backBtn.back.setOnClickListener {
            finish()
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
        binding.signUpTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun handleError(throwable: Throwable?) {}
}