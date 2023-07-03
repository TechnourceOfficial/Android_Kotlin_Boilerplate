package com.technource.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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

        initObj()
        click()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding
    }
}