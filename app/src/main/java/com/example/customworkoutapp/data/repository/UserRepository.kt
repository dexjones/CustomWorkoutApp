package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import com.example.customworkoutapp.data.dao.UserDao
import com.example.customworkoutapp.data.entities.User

class UserRepository(private val userDao: UserDao) {

    // Fetch user by email
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    // Insert or update user
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }
}
