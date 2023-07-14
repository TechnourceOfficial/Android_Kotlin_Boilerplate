package com.technource.android.ui.dashboardModule

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityDashboardBinding
import com.technource.android.base.BaseActivity
import com.technource.android.ui.homeModule.HomeFragment
import com.technource.android.ui.notificationModule.NotificationFragment
import com.technource.android.ui.orderModule.OrderFragment
import com.technource.android.ui.settingsModule.SettingsFragment

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun initObj() {
        // Load the HomeFragment as the initial fragment
        loadFragment(HomeFragment(this))

        // Disable icon tint for the bottom navigation items
        binding.bottomNav.itemIconTintList = null

        // Handle item selection in the bottom navigation view
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    // Load the HomeFragment when "Home" is selected
                    loadFragment(HomeFragment(this))
                    true
                }
                R.id.order -> {
                    // Load the OrderFragment when "Order" is selected
                    loadFragment(OrderFragment(this))
                    true
                }
                R.id.notification -> {
                    // Load the NotificationFragment when "Notification" is selected
                    loadFragment(NotificationFragment(this))
                    true
                }
                R.id.setting -> {
                    // Load the SettingFragment when "Setting" is selected
                    loadFragment(SettingsFragment(this))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun click() {}

    /**
    Loads the specified fragment into the container.
    @param fragment The fragment to be loaded.
     */
    private fun loadFragment(fragment: Fragment) {
        // Replace the container with the specified fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }


}