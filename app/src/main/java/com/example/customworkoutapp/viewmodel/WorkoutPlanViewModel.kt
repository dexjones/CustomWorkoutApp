package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.repository.WorkoutPlanRepository
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(private val workoutPlanRepository: WorkoutPlanRepository) : ViewModel() {

    // Fetch workout plans based on userId
    fun getWorkoutPlansForUser(userId: Int): LiveData<List<WorkoutPlan>> {
        return workoutPlanRepository.getWorkoutPlansForUser(userId)
    }

    // Insert workout plans (used when generating a workout plan)
    fun insertWorkoutPlan(workoutPlan: WorkoutPlan) {
        viewModelScope.launch {
            workoutPlanRepository.insertWorkoutPlan(workoutPlan)
        }
    }

    // Insert multiple workout plans
    fun insertWorkoutPlans(workoutPlans: List<WorkoutPlan>) {
        viewModelScope.launch {
            workoutPlanRepository.insertWorkoutPlans(workoutPlans)
        }
    }
}
