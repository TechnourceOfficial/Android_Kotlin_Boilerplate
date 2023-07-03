package com.technource.android.preference

import android.content.Context
import android.content.SharedPreferences
import com.technource.android.utils.Constants.Companion.APP_SHARED_PREFERENCE_NAME

class PreferencesHelperImpl constructor(context: Context) : PreferenceHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

}