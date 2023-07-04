package com.technource.android.ui.resetPasswordModule

import androidx.lifecycle.MutableLiveData
import com.technource.android.base.BaseViewModel
import com.technource.android.utils.ValidationStatus

class ResetPasswordViewModel : BaseViewModel<ResetPasswordNavigator>() {

    var passwordValidationStatus = MutableLiveData<ValidationStatus>()
    var confirmPasswordValidationStatus = MutableLiveData<ValidationStatus>()

    fun onLoginClicked() {
        getNavigator()!!.resetPassword()
    }
}