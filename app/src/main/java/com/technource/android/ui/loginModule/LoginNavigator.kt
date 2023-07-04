package com.technource.android.ui.loginModule

interface LoginNavigator {
    fun login()
    fun handleError(throwable: Throwable?)
}