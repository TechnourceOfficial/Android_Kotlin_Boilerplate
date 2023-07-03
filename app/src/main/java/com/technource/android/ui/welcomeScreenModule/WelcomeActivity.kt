package com.technource.android.ui.welcomeScreenModule

import android.content.Intent
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityWelcomeBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.ui.registrationModule.SignupActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {
    override fun getViewBinding() = ActivityWelcomeBinding.inflate(layoutInflater)

    override fun initObj() {}

    override fun click() {
        binding.login.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }

        binding.signUp.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignupActivity::class.java))
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }
}