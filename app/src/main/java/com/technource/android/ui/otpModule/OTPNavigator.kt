package com.technource.android.ui.otpModule

interface OTPNavigator {
    fun otp()
    fun handleError(throwable: Throwable?)
}