package com.technource.android.preference

import android.content.Context
import android.content.SharedPreferences
import com.technource.android.utils.Constants
import com.technource.android.utils.Constants.Companion.APP_SHARED_PREFERENCE_NAME

class PreferencesHelperImpl constructor(context: Context) : PreferenceHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

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
        mPrefs.edit().putBoolean(Constants.IS_INTRO_SCREEN_DONE, isIntroScreenDone).apply()
    }

    override fun isIntroScreenDone(): Boolean {
        return mPrefs.getBoolean(Constants.IS_INTRO_SCREEN_DONE, false)
    }

    override fun setIsLoggedIn(isLogin: Boolean) {
        mPrefs.edit().putBoolean(Constants.IS_LOGGED_IN, isLogin).apply()
    }

    override fun isLoggedIn(): Boolean {
        return mPrefs.getBoolean(Constants.IS_LOGGED_IN, false)
    }

    override fun clear() {
        mPrefs.edit().remove(Constants.IS_LOGGED_IN).apply()
        mPrefs.edit().remove(Constants.IS_INTRO_SCREEN_DONE).apply()
        mPrefs.edit().remove(Constants.LANGUAGE).apply()
        mPrefs.edit().remove(Constants.LANGUAGE_CODE).apply()
    }

    override fun setLoggedInEmail(email: String) {
        mPrefs.edit().putString(Constants.LOGGED_IN_USER_EMAIL, email).apply()
    }

    override fun getLoggedInEmail(): String {
        return mPrefs.getString(Constants.LOGGED_IN_USER_EMAIL, "").toString()
    }

    override fun setLoggedInUserId(userId: Long) {
        mPrefs.edit().putLong(Constants.LOGGED_IN_USER_ID, userId).apply()
    }

    override fun getLoggedInUserId(): Long {
        return mPrefs.getLong(Constants.LOGGED_IN_USER_ID, 0)
    }
}