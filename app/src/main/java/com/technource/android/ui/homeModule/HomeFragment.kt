package com.technource.android.ui.homeModule

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin_boilerplate.databinding.FragmentHomeBinding
import com.technource.android.base.BaseFragment
import com.technource.android.utils.hideKeyboard

class HomeFragment(private val mActivity: AppCompatActivity) : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override fun initObj() {}

    override fun click() {
        binding.searchImg.setOnClickListener {
            // hide search icon and visible search layout
            binding.searchLayout.visibility = View.VISIBLE
            binding.searchImg.visibility = View.GONE
        }

        binding.back.setOnClickListener {
            // hide search layout and visible search icon
            binding.searchLayout.visibility = View.GONE
            binding.searchImg.visibility = View.VISIBLE
            binding.searchET.text = null
            hideKeyboard(mActivity, it)
        }
    }
}