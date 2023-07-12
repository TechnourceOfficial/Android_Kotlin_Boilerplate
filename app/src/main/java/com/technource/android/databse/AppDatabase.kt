package com.technource.android.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
The AppDatabase class represents the Room database for the application.
It extends RoomDatabase class.
 */
@Database(entities = [RegistrationTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
    Retrieves the DAO (Data Access Object) for RegistrationTable.
    @return The RegistrationDao object.
     */
    abstract fun registrationDao(): RegistrationDao?

    companion object {
        const val DB_NAME = "AndroidBoilerplate"
        private var instance: AppDatabase? = null

        /**
         * Retrieves the singleton instance of AppDatabase.
         * @param context The application context.
         * @return The instance of AppDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DB_NAME
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}