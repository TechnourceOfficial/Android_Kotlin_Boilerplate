package com.technource.android.ui.splashScreenModule

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivitySplashBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.selectLanguageModule.SelectLanguageActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun initObj() {
        // Delayed execution using Handler to show the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, SelectLanguageActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }, 2000)
    }

    override fun click() {}
}