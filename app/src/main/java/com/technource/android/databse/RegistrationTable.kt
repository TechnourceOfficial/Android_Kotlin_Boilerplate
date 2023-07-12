package com.technource.android.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
The RegistrationTable data class represents a table in the database for storing registration information.
@property firstName The first name of the user.
@property lastName The last name of the user.
@property emailId The email ID of the user.
@property username The username of the user.
@property countryCode The country code of the user.
@property phoneNumber The phone number of the user.
@property password The password of the user.
@property homeAddress The home address of the user.
@property officeAddress The office address of the user.
@property userPhoto The URI of the user's photo.
@property id The ID of the user (auto-generated).
 */
@Entity
data class RegistrationTable(
    var firstName: String = "",
    var lastName: String = "",
    var emailId: String = "",
    var username: String = "",
    var countryCode: String = "",
    var phoneNumber: String = "",
    var password: String = "",
    var homeAddress: String = "",
    var officeAddress: String = "",
    var userPhoto: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}