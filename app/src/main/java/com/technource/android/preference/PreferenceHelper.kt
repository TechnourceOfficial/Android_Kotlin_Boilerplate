package com.technource.android.preference


interface PreferenceHelper {
    fun setLanguage(language: String)
    fun getLanguage(): String

    fun setLanguageCode(languageCode: String)
    fun getLanguageCode(): String

    fun setIsIntroScreenDone(isIntroScreenDone: Boolean)
    fun isIntroScreenDone(): Boolean
}