package com.technource.android.ui.editProfileModule

import androidx.lifecycle.MutableLiveData
import com.technource.android.base.BaseViewModel
import com.technource.android.utils.ValidationStatus

class EditProfileViewModel : BaseViewModel<EditProfileNavigator>() {
    var firstNameValidationStatus = MutableLiveData<ValidationStatus>()
    var lastNameValidationStatus = MutableLiveData<ValidationStatus>()
    var emailValidationStatus = MutableLiveData<ValidationStatus>()
    var usernameValidationStatus = MutableLiveData<ValidationStatus>()
    var homeAddressValidationStatus = MutableLiveData<ValidationStatus>()
    var officeAddressValidationStatus = MutableLiveData<ValidationStatus>()
    var mobileNoValidationStatus = MutableLiveData<ValidationStatus>()


    fun onLoginClicked() {
        getNavigator()!!.save()
    }
}