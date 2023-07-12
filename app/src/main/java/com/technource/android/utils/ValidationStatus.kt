package com.technource.android.utils

/**
Enum class representing the validation status for various fields.
 */
enum class ValidationStatus {
    VALID,
    EMPTY_EMAIL,
    INVALID_EMAIL,
    EMPTY_PASSWORD,
    INVALID_PASSWORD,
    EMPTY_CONFIRM_PASSWORD,
    MISMATCHED_PASSWORDS,
    EMPTY_FIRSTNAME,
    EMPTY_LASTNAME,
    EMPTY_USERNAME,
    EMPTY_HOME_ADDRESS,
    EMPTY_OFFICE_ADDRESS,
    EMPTY_MOBILENO,
    DUPLICATE_EMAIL
}