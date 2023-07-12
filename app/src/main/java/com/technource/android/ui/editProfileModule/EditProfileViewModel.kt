package com.technource.android.ui.editProfileModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.technource.android.base.BaseViewModel
import com.technource.android.databse.RegistrationDao
import com.technource.android.databse.RegistrationTable
import com.technource.android.utils.ValidationStatus
import kotlinx.coroutines.launch

class EditProfileViewModel : BaseViewModel<EditProfileNavigator>() {
    var firstNameValidationStatus = MutableLiveData<ValidationStatus>()
    var lastNameValidationStatus = MutableLiveData<ValidationStatus>()
    var emailValidationStatus = MutableLiveData<ValidationStatus>()
    var usernameValidationStatus = MutableLiveData<ValidationStatus>()
    var homeAddressValidationStatus = MutableLiveData<ValidationStatus>()
    var officeAddressValidationStatus = MutableLiveData<ValidationStatus>()
    var mobileNoValidationStatus = MutableLiveData<ValidationStatus>()

    private var registrationDao: RegistrationDao? = null

    private val _userProfile = MutableLiveData<RegistrationTable>()
    val userProfile: LiveData<RegistrationTable> = _userProfile

    /**
    Fetches the user profile from the database based on the specified email.
    @param email The email of the user.
     */
    fun fetchUserProfile(email: String) {
        viewModelScope.launch {
            val user = registrationDao?.getUserByEmail(email)
            _userProfile.value = user
        }
    }

    // Pass the RegistrationDao dependency to the ViewModel constructor
    fun initialize(registrationDao: RegistrationDao) {
        this.registrationDao = registrationDao
    }

    fun onLoginClicked() {
        getNavigator()!!.save()
    }
}