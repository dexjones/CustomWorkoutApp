package com.example.customworkoutapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    // Function to check if a user exists by email
    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    suspend fun checkUserExists(email: String): Int

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): LiveData<User?>
}