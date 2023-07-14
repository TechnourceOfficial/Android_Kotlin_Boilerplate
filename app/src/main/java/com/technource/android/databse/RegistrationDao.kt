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
    /**
    Retrieves the password associated with the given emailId.
    @param emailId The emailId of the user.
    @return The password associated with the emailId.
     */
    @Query("SELECT password FROM RegistrationTable WHERE emailId = :emailId")
    fun getPassword(emailId: String?): String
    /**
      Deletes the user entry with the given emailId from the table.
      @param emailId The emailId of the user to be deleted.
     */
    @Query("DELETE FROM RegistrationTable WHERE emailId = :emailId")
    fun deleteUser(emailId: String)
    /**
    Updates the selected language name and code for the user with the given emailId.
    @param selectedLanguageName The selected language name.
    @param selectedLanguageCode The selected language code.
    @param emailId The emailId of the user.
     */
    @Query("UPDATE RegistrationTable SET selectedLanguageName = :selectedLanguageName, selectedLanguageCode = :selectedLanguageCode WHERE emailId = :emailId")
    fun updateLanguage(selectedLanguageName: String, selectedLanguageCode: String, emailId: String)
}