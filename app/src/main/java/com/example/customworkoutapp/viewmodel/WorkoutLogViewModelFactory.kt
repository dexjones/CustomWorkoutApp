package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.data.repository.WorkoutRepository

class WorkoutLogViewModelFactory(private val workoutRepository: WorkoutRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutLogViewModel(workoutRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
