package com.technource.android.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ContentFrameLayout
import com.example.android_kotlin_boilerplate.R
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.reword.Reword
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

//Language Extension
/**
Changes the language of the activity to the specified locale.
@param context The context of the activity.
@param locale The locale to set as the desired language.
 */
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

/**
Displays an error toast with the specified message in the given activity.
@param message The message to be displayed in the toast.
@param activity The activity in which the toast should be shown.
 */
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

/**
Displays a success toast with the specified message in the given activity.
@param message The message to be displayed in the toast.
@param activity The activity in which the toast should be shown.
 */
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

/**
Checks if a password is valid based on the following criteria:
Contains at least one uppercase letter
Contains at least one lowercase letter
Contains at least one digit
Contains at least one special character from the set [!@#$&*~]
Has a minimum length of 8 characters
@param password The password to be validated.
@return true if the password is valid, false otherwise.
*/
fun isValidPassword(password: String): Boolean {
    val pattern: Pattern
    val PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#\\\$&*~]).{8,}\$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher: Matcher = pattern.matcher(password)
    return matcher.matches()
}

/**
Checks if an email address is valid.
@param email The email address to be validated.
@return true if the email address is valid, false otherwise.
 */
fun isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

/**
Validates an email address and returns the validation status.
@param email The email address to be validated.
@return The ValidationStatus enum indicating the validation status of the email.
 */
fun validateEmail(email: String?): ValidationStatus {
    return when {
        email.isNullOrEmpty() -> ValidationStatus.EMPTY_EMAIL
        !isValidEmail(email) -> ValidationStatus.INVALID_EMAIL
        else -> ValidationStatus.VALID
    }
}

/**
Validates a password and returns the validation status.
@param password The password to be validated.
@return The ValidationStatus enum indicating the validation status of the password.
 */
fun validatePassword(password: String?): ValidationStatus {
    return when {
        password.isNullOrEmpty() -> ValidationStatus.EMPTY_PASSWORD
        !isValidPassword(password) -> ValidationStatus.INVALID_PASSWORD
        else -> ValidationStatus.VALID
    }
}

/**
Validates a first name and returns the validation status.
@param v The first name to be validated.
@return The ValidationStatus enum indicating the validation status of the first name.
 */
fun validFirstName(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_FIRSTNAME
        else -> ValidationStatus.VALID
    }
}

/**
Validates a last name and returns the validation status.
@param v The last name to be validated.
@return The ValidationStatus enum indicating the validation status of the last name.
 */
fun validLastName(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_LASTNAME
        else -> ValidationStatus.VALID
    }
}

/**
Validates a username and returns the validation status.
@param v The username to be validated.
@return The ValidationStatus enum indicating the validation status of the username.
 */
fun validUsername(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_USERNAME
        else -> ValidationStatus.VALID
    }
}

/**
Validates a home address and returns the validation status.
@param v The home address to be validated.
@return The ValidationStatus enum indicating the validation status of the home address.
 */
fun validHomeAddress(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_USERNAME
        else -> ValidationStatus.VALID
    }
}

/**
Validates an office address and returns the validation status.
@param v The office address to be validated.
@return The ValidationStatus enum indicating the validation status of the office address.
 */
fun validOfficeAddress(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_USERNAME
        else -> ValidationStatus.VALID
    }
}

/**
Validates a mobile number and returns the validation status.
@param v The mobile number to be validated.
@return The ValidationStatus enum indicating the validation status of the mobile number.
 */
fun validMobile(v: String?): ValidationStatus {
    return when {
        v.isNullOrEmpty() -> ValidationStatus.EMPTY_MOBILENO
        else -> ValidationStatus.VALID
    }
}

/**
Validates the confirm password and returns the validation status.
@param password The password entered by the user.
@param confirmPassword The confirm password entered by the user.
@return The ValidationStatus enum indicating the validation status of the confirm password.
 */
fun validConfirmPassword(password: String?, confirmPassword: String?): ValidationStatus {
    // validate confirm password
    if (TextUtils.isEmpty(confirmPassword)) {
        return ValidationStatus.EMPTY_CONFIRM_PASSWORD
    }
    if (password != confirmPassword) {
        return ValidationStatus.MISMATCHED_PASSWORDS
    }
    return ValidationStatus.VALID
}

/**
Retrieves the error message for a specific ValidationStatus.
@param context The context used to retrieve the string resources.
@return The error message corresponding to the ValidationStatus.
 */
fun ValidationStatus.getErrorMessage(context: Context): String? {
    val errorMessageResId = ValidationConstants.validationStatusErrorMap[this]
    return errorMessageResId?.let { context.getString(it) }
}

/**
Hides the keyboard.
@param context The context used to retrieve the input method manager.
@param view The view from which to retrieve the window token.
 */
fun hideKeyboard(context: Context, view: View) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
