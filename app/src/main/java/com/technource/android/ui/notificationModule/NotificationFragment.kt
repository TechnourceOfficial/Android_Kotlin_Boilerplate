package com.technource.android.ui.notificationModule

import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin_boilerplate.databinding.FragmentNotificationBinding
import com.technource.android.base.BaseFragment

class NotificationFragment(private val mActivity: AppCompatActivity) :
    BaseFragment<FragmentNotificationBinding>() {
    override fun getViewBinding() = FragmentNotificationBinding.inflate(layoutInflater)

    override fun initObj() {}

    override fun click() {}

}