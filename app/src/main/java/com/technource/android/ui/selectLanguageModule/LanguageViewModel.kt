package com.technource.android.ui.selectLanguageModule

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin_boilerplate.R

class LanguageViewModel : ViewModel() {
    private val selectedLanguage = MutableLiveData<String>()

    /**
    Retrieves the list of available languages from the app resources.
    @param context The context of the app.
    @return An array of strings representing the available languages.
     */
    fun getLanguages(context: Context): Array<String> {
        return context.resources.getStringArray(R.array.language)
    }

    /**
    Sets the selected language.
    @param language The language to be set as the selected language.
     */
    fun setSelectedLanguage(language: String) {
        selectedLanguage.value = language
    }
}