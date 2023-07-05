package com.technource.android.preference


interface PreferenceHelper {
    /**
    Sets the selected language.
    @param language The language to be set.
    */
    fun setLanguage(language: String)
    /**
    Retrieves the selected language.
    @return The selected language.
    */
    fun getLanguage(): String
    /**
    Sets the language code for localization purposes.
    @param languageCode The language code to be set.
    */
    fun setLanguageCode(languageCode: String)
    /**
    Retrieves the language code for localization.
    @return The language code.
    */
    fun getLanguageCode(): String
    /**
    Sets the flag indicating whether the intro screen has been completed.
    @param isIntroScreenDone True if intro screen is completed, false otherwise.
     */
    fun setIsIntroScreenDone(isIntroScreenDone: Boolean)
    /**
    Checks if the intro screen has been completed.
    @return True if intro screen is completed, false otherwise.
     */
    fun isIntroScreenDone(): Boolean
    fun setIsLoggedIn(isLogin: Boolean)
    fun isLoggedIn(): Boolean
    fun clear()
}