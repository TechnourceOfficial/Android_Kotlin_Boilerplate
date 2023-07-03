package com.technource.android.ui.selectLanguageModule

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin_boilerplate.R

class LanguageViewModel : ViewModel() {
    private val selectedLanguage = MutableLiveData<String>()

    fun getLanguages(context: Context): Array<String> {
        return context.resources.getStringArray(R.array.language)
    }

    fun setSelectedLanguage(language: String) {
        selectedLanguage.value = language
    }
}