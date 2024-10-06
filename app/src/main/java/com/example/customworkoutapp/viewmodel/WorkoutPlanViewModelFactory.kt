package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.data.repository.WorkoutPlanRepository

class WorkoutPlanViewModelFactory(private val workoutPlanRepository: WorkoutPlanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutPlanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutPlanViewModel(workoutPlanRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
