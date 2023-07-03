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
}