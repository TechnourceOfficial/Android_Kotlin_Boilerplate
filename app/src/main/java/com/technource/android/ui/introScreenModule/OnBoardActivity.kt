package com.technource.android.ui.introScreenModule

import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityOnBoardBinding
import com.technource.android.base.BaseActivity
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.welcomeScreenModule.WelcomeActivity

class OnBoardActivity : BaseActivity<ActivityOnBoardBinding>() {
    override fun getViewBinding() = ActivityOnBoardBinding.inflate(layoutInflater)
    var currentPos = 0
    private lateinit var preference: PreferencesHelperImpl

    override fun initObj() {
        preference = PreferencesHelperImpl(this)
        val introPagerAdapter = IntroScreenAdapter(this@OnBoardActivity)
        binding.viewPager.adapter = introPagerAdapter

        // set default selected dot position
        addDots(0)
        binding.viewPager.addOnPageChangeListener(changeListener)
    }

    override fun click() {
        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem == binding.viewPager.adapter!!.count - 1) {
                preference.setIsIntroScreenDone(true)
                startActivity(Intent(this@OnBoardActivity, WelcomeActivity::class.java))
                finish()
                overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
            }
            binding.viewPager.currentItem = currentPos + 1
        }

        binding.btnSkip.setOnClickListener {
            preference.setIsIntroScreenDone(true)
            startActivity(Intent(this@OnBoardActivity, WelcomeActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    private fun addDots(position: Int) {
        // Create an array of TextViews to represent the dots
        val dots = binding.viewPager.adapter?.let { arrayOfNulls<TextView>(it.count) }
        binding.dotsLayout.removeAllViews()     // Remove all existing views from the dots layout
        if (dots != null) {         // Check if the dots array is not null
            for (i in dots.indices) {        // Iterate through the dots array
                dots[i] = TextView(this)        // Create a new TextView for each dot
                dots[i]!!.text =
                    Html.fromHtml("&#8226;")       // Set the text of the dot as a bullet point using HTML encoding
                dots[i]!!.textSize = 35f        // Set the text size of the dot
                dots[i]!!.setTextColor(Color.TRANSPARENT)        // Set the text color of the dot to transparent
                dots[i]!!.background =
                    if (i != position) {        // Set the background of the dot based on its position
                        resources.getDrawable(R.drawable.active_dot)
                    } else {
                        resources.getDrawable(R.drawable.circle_background)
                    }

                // Create layout parameters for the dot
                val params = if (i == position) LinearLayout.LayoutParams(
                    50,
                    50
                ) else LinearLayout.LayoutParams(35, 35)
                // Set margins for the dot based on its position
                if (i == position) params.setMargins(5, 5, 5, 5) else params.setMargins(5, 10, 5, 5)
                dots[i]!!.layoutParams = params         // Set the layout parameters for the dot
                binding.dotsLayout.addView(dots[i])     // Add the dot to the dots layout
            }
        }

    }

    private var changeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPos = position
            when (position) {
                0 -> {
                    binding.btnSkip.visibility = View.VISIBLE
                }
                1 -> {
                    binding.btnSkip.visibility = View.VISIBLE
                }
                2 -> {
                    binding.btnSkip.visibility = View.INVISIBLE
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}