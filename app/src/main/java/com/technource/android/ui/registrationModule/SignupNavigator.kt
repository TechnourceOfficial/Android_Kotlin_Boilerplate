package com.technource.android.ui.registrationModule

interface SignupNavigator {
    fun signUp()
    fun handleError(throwable: Throwable?)
}