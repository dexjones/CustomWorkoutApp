package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.security.MessageDigest

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(entity = Goal::class, parentColumns = ["id"], childColumns = ["goalId"], onDelete = ForeignKey.NO_ACTION)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val passwordHash: String,
    val salt: String,
    val name: String = "",
    val age: Int? = null,
    val weight: Float? = null,
    val height: Float? = null,
    val goalId: Int? = null,  // Link to the user's goal
    val isProfileComplete: Boolean = false  // Flag for profile completion
) {
    companion object {
        // Function to generate a random salt
        fun generateSalt(): String {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            return (1..16).map { chars.random() }.joinToString("")
        }

        // Function to hash the password with salt using SHA-256
        fun hashPassword(password: String, salt: String): String {
            val messageDigest = MessageDigest.getInstance("SHA-256")
            val hashBytes = messageDigest.digest((password + salt).toByteArray(Charsets.UTF_8))
            return hashBytes.joinToString("") { "%02x".format(it) }
        }
    }

    /**
     * Function to check if all required profile fields are filled out.
     * This is used to determine whether the profile is complete.
     */
    fun checkProfileCompletion(): Boolean {
        return name.isNotBlank() && age != null && weight != null && height != null && goalId != null
    }
}
