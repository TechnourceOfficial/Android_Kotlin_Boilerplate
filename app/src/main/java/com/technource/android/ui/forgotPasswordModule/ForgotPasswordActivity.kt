package com.technource.android.ui.forgotPasswordModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityForgotPasswordBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.ui.otpModule.OtpActivity
import com.technource.android.utils.ValidationStatus
import com.technource.android.utils.getErrorMessage
import com.technource.android.utils.validateEmail

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>(),
    ForgotPasswordNavigator {
    override fun getViewBinding() = ActivityForgotPasswordBinding.inflate(layoutInflater)
    private lateinit var viewModel: ForgotPasswordViewModel
    override fun initObj() {

        binding.headerLayout.headerTitle.text = resources.getString(R.string.forgot_password_title)
        // Initialize the view model
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]

        // Set the view model and lifecycle owner for data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Set the navigator for the view model
        viewModel.setNavigator(this)

        // Observe the email validation status
        viewModel.emailValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        }

        // Add text changed listener for the email input field
        binding.emailET.addTextChangedListener {
            val email = it.toString().trim()
            val emailStatus = validateEmail(email)
            viewModel.emailValidationStatus.value = emailStatus
        }
    }

    override fun click() {
        binding.headerLayout.backBtn.setOnClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun forgotPassword() {
        // Retrieve the entered email
        val email: String = binding.emailET.text.toString()

        // Validate the email
        val emailStatus = validateEmail(email)
        if (emailStatus != ValidationStatus.VALID) {
            // Display error message if email is not valid
            val errorMessage = emailStatus.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        } else {
            // Start OTPActivity if email is valid
            startActivity(Intent(this@ForgotPasswordActivity, OtpActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun handleError(throwable: Throwable?) {}

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
    }
}