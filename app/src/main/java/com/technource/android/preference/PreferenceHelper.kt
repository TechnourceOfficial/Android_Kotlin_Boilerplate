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
    /**
    Sets the flag indicating whether the user is logged in.
    @param isLogin True if the user is logged in, false otherwise.
     */
    fun setIsLoggedIn(isLogin: Boolean)
    /**
    Checks if the user is logged in.
    @return True if the user is logged in, false otherwise.
     */
    fun isLoggedIn(): Boolean
    /**
    Clears all the stored preferences.
     */
    fun clear()
    /**
    Sets the email of the currently logged in user.
    @param email The email of the user.
     */
    fun setLoggedInEmail(email: String)
    /**
    Retrieves the email of the currently logged in user.
    @return The email of the user.
     */
    fun getLoggedInEmail(): String
    /**
    Sets the userId of the currently logged in user.
    @param userId The userId of the user.
     */
    fun setLoggedInUserId(userId: Long)
    /**
    Retrieves the userId of the currently logged in user.
    @return The userId of the user.
     */
    fun getLoggedInUserId(): Long
}