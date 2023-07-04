package com.technource.android.ui.resetPasswordModule

interface ResetPasswordNavigator {
    fun resetPassword()
    fun handleError(throwable: Throwable?)
}