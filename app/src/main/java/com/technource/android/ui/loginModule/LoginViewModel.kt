package com.technource.android.ui.loginModule

import androidx.lifecycle.MutableLiveData
import com.technource.android.base.BaseViewModel
import com.technource.android.utils.ValidationStatus


class LoginViewModel : BaseViewModel<LoginNavigator>() {

    var emailValidationStatus = MutableLiveData<ValidationStatus>()
    var passwordValidationStatus = MutableLiveData<ValidationStatus>()

    fun onLoginClicked() {
        getNavigator()!!.login()
    }
}