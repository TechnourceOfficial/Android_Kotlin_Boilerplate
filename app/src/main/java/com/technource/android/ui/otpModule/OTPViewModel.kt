package com.technource.android.ui.otpModule

import com.technource.android.base.BaseViewModel

class OTPViewModel : BaseViewModel<OTPNavigator>() {
    fun onLoginClicked() {
        getNavigator()!!.otp()
    }
}