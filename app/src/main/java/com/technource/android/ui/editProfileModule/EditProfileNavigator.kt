package com.technource.android.ui.editProfileModule

interface EditProfileNavigator {
    fun save()
    fun handleError(throwable: Throwable?)
}