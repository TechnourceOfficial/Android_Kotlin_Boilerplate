package com.technource.android.ui.settingsModule

import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin_boilerplate.databinding.FragmentSettingsBinding
import com.technource.android.base.BaseFragment

class SettingsFragment(private val mActivity: AppCompatActivity) :
    BaseFragment<FragmentSettingsBinding>() {
    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    override fun initObj() {}

    override fun click() {}
}