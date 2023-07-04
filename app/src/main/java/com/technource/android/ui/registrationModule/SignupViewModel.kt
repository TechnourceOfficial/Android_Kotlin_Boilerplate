package com.technource.android.ui.registrationModule

import androidx.lifecycle.MutableLiveData
import com.technource.android.base.BaseViewModel
import com.technource.android.utils.ValidationStatus

class SignupViewModel : BaseViewModel<SignupNavigator>() {
    var firstNameValidationStatus = MutableLiveData<ValidationStatus>()
    var lastNameValidationStatus = MutableLiveData<ValidationStatus>()
    var emailValidationStatus = MutableLiveData<ValidationStatus>()
    var usernameValidationStatus = MutableLiveData<ValidationStatus>()
    var mobileNoValidationStatus = MutableLiveData<ValidationStatus>()
    var newPasswordValidationStatus = MutableLiveData<ValidationStatus>()
    var confirmPassValidationStatus = MutableLiveData<ValidationStatus>()


    fun onLoginClicked() {
        getNavigator()!!.signUp()
    }
}