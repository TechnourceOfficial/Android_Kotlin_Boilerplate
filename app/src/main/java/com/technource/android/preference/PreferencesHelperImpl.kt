package com.technource.android.preference

import android.content.Context
import android.content.SharedPreferences
import com.technource.android.utils.Constants.Companion.APP_SHARED_PREFERENCE_NAME

class PreferencesHelperImpl constructor(context: Context) : PreferenceHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        const val LANGUAGE = "language"
        const val LANGUAGE_CODE = "language_code"
        const val IS_INTRO_SCREEN_DONE = "is_intro_screen_done"
    }

    override fun setLanguage(language: String) {
        mPrefs.edit().putString(LANGUAGE, language).apply()
    }

    override fun getLanguage(): String {
        return mPrefs.getString(LANGUAGE, "english").toString()
    }

    override fun setLanguageCode(languageCode: String) {
        mPrefs.edit().putString(LANGUAGE_CODE, languageCode).apply()
    }

    override fun getLanguageCode(): String {
        return mPrefs.getString(LANGUAGE_CODE, "en").toString()
    }

    override fun setIsIntroScreenDone(isIntroScreenDone: Boolean) {
        mPrefs.edit().putBoolean(IS_INTRO_SCREEN_DONE, isIntroScreenDone).apply()
    }

    override fun isIntroScreenDone(): Boolean {
        return mPrefs.getBoolean(IS_INTRO_SCREEN_DONE, false)
    }
}