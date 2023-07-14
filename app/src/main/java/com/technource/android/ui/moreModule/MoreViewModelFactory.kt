package com.technource.android.ui.moreModule

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoreViewModelFactory(private val resources: Resources) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoreViewModel::class.java)) {
            return MoreViewModel(resources) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}