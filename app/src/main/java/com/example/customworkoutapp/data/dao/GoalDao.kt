package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.Goal

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal): Long

    @Query("SELECT * FROM goals WHERE id = :id")
    suspend fun getGoalById(id: Int): Goal?
}
