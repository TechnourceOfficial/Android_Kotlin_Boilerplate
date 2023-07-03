package com.technource.android.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ContentFrameLayout
import com.example.android_kotlin_boilerplate.R
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.reword.Reword
import java.util.*

//Language Extension
fun Activity.changeLanguage(context: Context, locale: Locale) {
    // Set the desired locale in the AppLocale class
    AppLocale.desiredLocale = locale

    // Create a new Configuration object and set the locale
    val config = Configuration()
    config.locale = locale

    // Update the configuration of the resources with the new locale
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    // Get the root view of the activity's window
    val rootView =
        this.window?.decorView?.findViewById<ContentFrameLayout>(android.R.id.content)

    // Reword any text or UI elements that require localization
    Reword.reword(rootView!!)

    // Set the layout direction to left-to-right
    window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR

    // Invalidate the root view to redraw the UI with the new locale
    rootView.invalidate()
    rootView.requestLayout()
}

fun Toast.errorToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.error_toast,
        activity.findViewById(R.id.error_toast_root)
    )

    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.error_message)
    textView.text = message

    // use the application extension function
    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

fun Toast.successToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.success_toast,
        activity.findViewById(R.id.success_toast_root)
    )

    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.success_message)
    textView.text = message

    // use the application extension function
    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}
