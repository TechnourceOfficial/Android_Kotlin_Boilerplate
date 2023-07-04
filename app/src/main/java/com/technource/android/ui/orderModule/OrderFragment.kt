package com.technource.android.ui.orderModule

import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin_boilerplate.databinding.FragmentOrderBinding
import com.technource.android.base.BaseFragment


class OrderFragment(private val mActivity: AppCompatActivity) :
    BaseFragment<FragmentOrderBinding>() {
    override fun getViewBinding() = FragmentOrderBinding.inflate(layoutInflater)

    override fun initObj() {}

    override fun click() {}
}