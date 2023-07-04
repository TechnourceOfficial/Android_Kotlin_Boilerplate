package com.technource.android.ui.otpModule

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityOtpBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.ui.resetPasswordModule.ResetPasswordActivity

class OtpActivity : BaseActivity<ActivityOtpBinding>(), OTPNavigator {
    override fun getViewBinding() = ActivityOtpBinding.inflate(layoutInflater)
    private lateinit var viewModel: OTPViewModel
    val pinLength = 6
    override fun initObj() {
        binding.headerLayout.headerTitle.text = resources.getString(R.string.otp_verification)
        // Initialize OTPViewModel instance
        viewModel = ViewModelProvider(this)[OTPViewModel::class.java]
        // Set viewModel and lifecycle owner for data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //set viewModel navigator
        viewModel.setNavigator(this)

        // Add a text changed listener to the pinView
        binding.pinview.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pin = s.toString()
                // Check if the pin length is not equal to pinLength
                if (pin.length != pinLength) {
                    binding.pinErrorTv.visibility = View.VISIBLE
                } else {
                    binding.pinErrorTv.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun click() {
        binding.headerLayout.backBtn.setOnClickListener {
            startActivity(Intent(this@OtpActivity, LoginActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun otp() {
        val pin = binding.pinview.text.toString()
        // Check if the pin length is not equal to pinLength
        if (pin.length != pinLength) {
            binding.pinErrorTv.visibility = View.VISIBLE
        } else {
            binding.pinErrorTv.visibility = View.GONE
            startActivity(Intent(this@OtpActivity, ResetPasswordActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun handleError(throwable: Throwable?) {}

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        startActivity(Intent(this@OtpActivity, LoginActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
    }
}