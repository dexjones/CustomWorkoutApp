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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlans(workoutPlans: List<WorkoutPlan>)

    @Query("SELECT * FROM workout_plan WHERE userId = :userId")
    fun getWorkoutPlansForUser(userId: Int): LiveData<List<WorkoutPlan>>
}
