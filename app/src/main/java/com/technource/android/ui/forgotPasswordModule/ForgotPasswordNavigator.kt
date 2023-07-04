package com.technource.android.ui.forgotPasswordModule

interface ForgotPasswordNavigator {
    fun forgotPassword()
    fun handleError(throwable: Throwable?)
}