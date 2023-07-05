package com.technource.android.preference

import android.content.Context
import android.content.SharedPreferences
import com.technource.android.utils.Constants
import com.technource.android.utils.Constants.Companion.APP_SHARED_PREFERENCE_NAME

class PreferencesHelperImpl constructor(context: Context) : PreferenceHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        const val IS_INTRO_SCREEN_DONE = "is_intro_screen_done"
        const val IS_LOGGED_IN = "is_logged_in"
    }

    override fun setLanguage(language: String) {
        mPrefs.edit().putString(Constants.LANGUAGE, language).apply()
    }

    override fun getLanguage(): String {
        return mPrefs.getString(Constants.LANGUAGE, "english").toString()
    }

    override fun setLanguageCode(languageCode: String) {
        mPrefs.edit().putString(Constants.LANGUAGE_CODE, languageCode).apply()
    }

    override fun getLanguageCode(): String {
        return mPrefs.getString(Constants.LANGUAGE_CODE, "en").toString()
    }

    override fun setIsIntroScreenDone(isIntroScreenDone: Boolean) {
        mPrefs.edit().putBoolean(IS_INTRO_SCREEN_DONE, isIntroScreenDone).apply()
    }

    override fun isIntroScreenDone(): Boolean {
        return mPrefs.getBoolean(IS_INTRO_SCREEN_DONE, false)
    }

    override fun setIsLoggedIn(isLogin: Boolean) {
        mPrefs.edit().putBoolean(IS_LOGGED_IN, isLogin).apply()
    }

    override fun isLoggedIn(): Boolean {
        return mPrefs.getBoolean(IS_LOGGED_IN, false)
    }

    override fun clear() {
        mPrefs.edit().remove(IS_LOGGED_IN).apply()
        mPrefs.edit().remove(IS_INTRO_SCREEN_DONE).apply()
        mPrefs.edit().remove(Constants.LANGUAGE).apply()
        mPrefs.edit().remove(Constants.LANGUAGE_CODE).apply()
    }
}