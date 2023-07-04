package com.technource.android.ui.forgotPasswordModule

import androidx.lifecycle.MutableLiveData
import com.technource.android.base.BaseViewModel
import com.technource.android.utils.ValidationStatus

class ForgotPasswordViewModel : BaseViewModel<ForgotPasswordNavigator>() {

    var emailValidationStatus = MutableLiveData<ValidationStatus>()

    fun onLoginClicked() {
        getNavigator()!!.forgotPassword()
    }
}