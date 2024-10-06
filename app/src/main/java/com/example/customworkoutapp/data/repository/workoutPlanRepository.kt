package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import com.example.customworkoutapp.data.dao.WorkoutPlanDao
import com.example.customworkoutapp.data.entities.WorkoutPlan

class WorkoutPlanRepository(private val workoutPlanDao: WorkoutPlanDao) {

    fun getWorkoutPlansForUser(userId: Int): LiveData<List<WorkoutPlan>> {
        return workoutPlanDao.getWorkoutPlansForUser(userId)
    }

    // Insert a workout plan
    suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan) {
        workoutPlanDao.insertWorkoutPlan(workoutPlan)
    }
    // Insert a list of workout plans
    suspend fun insertWorkoutPlans(workoutPlans: List<WorkoutPlan>) {
        workoutPlanDao.insertWorkoutPlans(workoutPlans)
    }
}
