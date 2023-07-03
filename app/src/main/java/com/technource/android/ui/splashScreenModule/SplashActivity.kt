package com.technource.android.ui.splashScreenModule

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivitySplashBinding
import com.technource.android.base.BaseActivity
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.selectLanguageModule.SelectLanguageActivity
import com.technource.android.utils.LocaleHelper

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)
    private lateinit var preference: PreferencesHelperImpl
    override fun initObj() {
        // Initialize the preference helper
        preference = PreferencesHelperImpl(this)

        // Set the locale based on the saved language code in preferences
        when (preference.getLanguageCode()) {
            resources.getString(R.string.english_code) -> {
                LocaleHelper.setLocale(this, resources.getString(R.string.english_code))
            }
            resources.getString(R.string.french_code) -> {
                LocaleHelper.setLocale(this, resources.getString(R.string.french_code))
            }
            resources.getString(R.string.russian_code) -> {
                LocaleHelper.setLocale(this, resources.getString(R.string.russian_code))
            }
        }
        // Delayed execution using Handler to show the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, SelectLanguageActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }, 2000)
    }

    override fun click() {}
}