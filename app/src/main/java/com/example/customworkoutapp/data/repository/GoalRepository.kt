package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.example.customworkoutapp.data.dao.GoalDao
import com.example.customworkoutapp.data.entities.Goal

class GoalRepository(private val goalDao: GoalDao) {

    // Method to insert a goal into the database
    suspend fun insertGoal(goal: Goal): Long {
        return goalDao.insertGoal(goal)
    }

    fun getGoalForUser(userId: Int): LiveData<Goal> {
        return goalDao.getGoalForUser(userId)
    }
}
