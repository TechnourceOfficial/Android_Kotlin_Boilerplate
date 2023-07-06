package com.technource.android.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

/**
Helper class to manage the application's locale and language settings.
 */
object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    /**
    Sets the locale for the application.
    @param context The context.
    @param language The language code (e.g., "en", "fr").
    @return The updated context with the new locale.
     */
    fun setLocale(context: Context, language: String): Context {
        persist(context, language)

        // updating the language for devices above android nougat
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
        // for devices having lower version of android os
    }

    /**
    Persists the selected language in the shared preferences.
    @param context The context.
    @param language The selected language.
     */
    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    /**
    Updates the resources configuration with the specified language for devices running on Android Nougat (API level 24) or higher.
    @param context The context.
    @param language The language code (e.g., "en", "fr").
    @return The updated context with the new resources configuration.
     */
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    /**
    Updates the resources configuration with the specified language for devices running on Android versions lower than Nougat (API level 24).
    @param context The context.
    @param language The language code (e.g., "en", "fr").
    @return The updated context with the new resources configuration.
     */
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}