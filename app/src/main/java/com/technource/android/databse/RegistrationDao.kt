package com.technource.android.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
The RegistrationDao interface is a Data Access Object (DAO) for performing database operations related to RegistrationTable.
 */
@Dao
interface RegistrationDao {
    /**
    Inserts data into the RegistrationTable.
    @param registrationTable The RegistrationTable object to be inserted.
     */
    @Insert
    fun insertData(registrationTable: RegistrationTable)
    /**
    Checks if an email exists in the RegistrationTable.
    @param emailId The email to check.
    @return true if the email exists, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT * FROM RegistrationTable WHERE emailId = :emailId)")
    fun isEmailExists(emailId: String?): Boolean
    /**
    Performs login by checking the email and password in the RegistrationTable.
    @param emailId The email for login.
    @param password The password for login.
    @return The RegistrationTable object if login is successful, null otherwise.
     */
    @Query("SELECT * FROM RegistrationTable WHERE emailId = :emailId AND password = :password")
    fun login(emailId: String, password: String): RegistrationTable?
    /**
    Retrieves a user from the RegistrationTable based on the email.
    @param email The email of the user to retrieve.
    @return The RegistrationTable object if a user with the specified email exists, null otherwise.
     */
    @Query("SELECT * FROM RegistrationTable WHERE emailId = :email")
    fun getUserByEmail(email: String): RegistrationTable?
    /**
    Updates a user in the RegistrationTable.
    @param userId The ID of the user to update.
    @param firstName The new first name.
    @param lastName The new last name.
    @param emailId The new email.
    @param username The new username.
    @param countryCode The new country code.
    @param phoneNumber The new phone number.
    @param homeAddress The new home address.
    @param officeAddress The new office address.
    @param userPhotoURI The new user photo URI.
     */
    @Query("UPDATE RegistrationTable SET firstName = :firstName, lastName = :lastName, emailId = :emailId, username = :username, countryCode =:countryCode, phoneNumber = :phoneNumber, homeAddress = :homeAddress, officeAddress = :officeAddress, userPhoto =:userPhotoURI WHERE id = :userId")
    fun updateUser(
        userId: Long,
        firstName: String,
        lastName: String,
        emailId: String,
        username: String,
        countryCode: String,
        phoneNumber: String,
        homeAddress: String,
        officeAddress: String,
        userPhotoURI: String
    )
    /**
    Resets the password of a user in the RegistrationTable.
    @param password The new password.
    @param emailId The email of the user to reset the password.
     */
    @Query("UPDATE RegistrationTable SET password = :password WHERE emailId = :emailId")
    fun resetPassword(password: String, emailId: String)
}