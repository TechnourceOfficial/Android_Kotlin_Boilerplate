package com.technource.android.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.android_kotlin_boilerplate.R

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    abstract fun getViewBinding(): B
    lateinit var binding: B
    abstract fun initObj()
    abstract fun click()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::binding.isInitialized.not()) {
            binding = getViewBinding()
            setContentView(binding.root)
        }
        val backgroundColor = resources.getColor(R.color.kcPrimaryColor)

        val isLightStatusBar = isColorLight(backgroundColor)

        // Function to check if a color is light or dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.kcPrimaryColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val flags = window.decorView.systemUiVisibility
            if (isLightStatusBar) {
                window.decorView.systemUiVisibility = flags or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = flags and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
        initObj()
        click()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding
    }

    private fun isColorLight(color: Int): Boolean {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val brightness = (red * 299 + green * 587 + blue * 114) / 1000
        return brightness > 127
    }
}