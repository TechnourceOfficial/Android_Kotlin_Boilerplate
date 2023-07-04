package com.technource.android.ui.resetPasswordModule

import android.content.Intent
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityResetPasswordBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.ui.otpModule.OtpActivity
import com.technource.android.utils.ValidationStatus
import com.technource.android.utils.getErrorMessage
import com.technource.android.utils.validConfirmPassword
import com.technource.android.utils.validatePassword

class ResetPasswordActivity : BaseActivity<ActivityResetPasswordBinding>(), ResetPasswordNavigator {
    override fun getViewBinding() = ActivityResetPasswordBinding.inflate(layoutInflater)
    private lateinit var viewModel: ResetPasswordViewModel

    override fun initObj() {
        binding.headerLayout.headerTitle.text = resources.getString(R.string.set_new_password)
        // Initialize ResetPasswordViewModel instance
        viewModel = ViewModelProvider(this)[ResetPasswordViewModel::class.java]

        // Set viewModel and lifecycle owner for data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.setNavigator(this)

        // Observe the password validation status
        viewModel.passwordValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.passwordTextInput.error = errorMessage
            binding.passwordTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the confirm password validation status
        viewModel.confirmPasswordValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.confirmPasswordTextInput.error = errorMessage
            binding.confirmPasswordTextInput.isErrorEnabled = errorMessage != null
        }

        // Add a text changed listener to the password field
        binding.passwordET.addTextChangedListener {
            val password = it.toString().trim()
            val passwordStatus = validatePassword(password)
            viewModel.passwordValidationStatus.value = passwordStatus
        }

        // Add a text changed listener to the confirm password field
        binding.confirmPasswordET.addTextChangedListener {
            val confirmPassword = it.toString().trim()
            val confirmPasswordStatus =
                validConfirmPassword(binding.passwordET.text.toString(), confirmPassword)
            viewModel.confirmPasswordValidationStatus.value = confirmPasswordStatus
        }
    }

    override fun click() {
        binding.headerLayout.backBtn.setOnClickListener {
            startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun resetPassword() {
        val newPassword: String = binding.passwordET.text.toString()
        val confirmPassword: String = binding.confirmPasswordET.text.toString()

        // Validate the new password and confirm password status
        val newPasswordStatus = validatePassword(newPassword)
        val confirmPasswordStatus = validConfirmPassword(newPassword, confirmPassword)

        // Check if both password and confirm password are valid
        if (newPasswordStatus == ValidationStatus.VALID &&
            confirmPasswordStatus == ValidationStatus.VALID
        ) {
            startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }

        // Show error message for invalid new password
        if (newPasswordStatus != ValidationStatus.VALID) {
            val errorMessage = newPasswordStatus.getErrorMessage(this)
            binding.passwordTextInput.error = errorMessage
            binding.passwordTextInput.isErrorEnabled = errorMessage != null
        }

        // Show error message for invalid confirm password
        if (confirmPasswordStatus != ValidationStatus.VALID) {
            val errorMessage = confirmPasswordStatus.getErrorMessage(this)
            binding.confirmPasswordTextInput.error = errorMessage
            binding.confirmPasswordTextInput.isErrorEnabled = errorMessage != null
        }
    }

    override fun handleError(throwable: Throwable?) {}

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
    }
}