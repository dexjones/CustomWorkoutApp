package com.example.customworkoutapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.WorkoutPlan

@Dao
interface WorkoutPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workout_plans WHERE userId = :userId")
    fun getWorkoutPlansByUser(userId: Int): LiveData<List<WorkoutPlan>>
}