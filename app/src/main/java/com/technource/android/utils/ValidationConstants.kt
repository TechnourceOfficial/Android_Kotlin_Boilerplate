package com.technource.android.utils

import com.example.android_kotlin_boilerplate.R


object ValidationConstants {
    // The validationStatusErrorMap is a map that maps each validation status to its corresponding error message resource ID.
    val validationStatusErrorMap = mapOf(
        ValidationStatus.EMPTY_EMAIL to R.string.please_enter_email,
        ValidationStatus.INVALID_EMAIL to R.string.valid_email,
        ValidationStatus.EMPTY_PASSWORD to R.string.please_enter_password,
        ValidationStatus.INVALID_PASSWORD to R.string.please_enter_valid_password,
        ValidationStatus.EMPTY_FIRSTNAME to R.string.empty_firstname,
        ValidationStatus.EMPTY_LASTNAME to R.string.empty_lastname,
        ValidationStatus.EMPTY_USERNAME to R.string.empty_username,
        ValidationStatus.EMPTY_MOBILENO to R.string.empty_mobile,
        ValidationStatus.EMPTY_CONFIRM_PASSWORD to R.string.empty_confirm_password,
        ValidationStatus.MISMATCHED_PASSWORDS to R.string.password_not_match,
        ValidationStatus.EMPTY_HOME_ADDRESS to R.string.empty_home_address,
        ValidationStatus.EMPTY_OFFICE_ADDRESS to R.string.empty_home_address,

        )
}