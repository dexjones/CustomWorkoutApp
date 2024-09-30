package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.MessageDigest

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val passwordHash: String,
    val salt: String,
    val name: String,
    val age: Int? = null,
    val weight: Float? = null,
    val height: Float? = null,
    val goal: String? = null
)
{
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
}