package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.data.repository.ExerciseRepository
import com.example.customworkoutapp.data.repository.GoalRepository

class GoalViewModelFactory(
    private val goalRepository: GoalRepository,
    private val exerciseRepository: ExerciseRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalViewModel::class.java)) {
            return GoalViewModel(goalRepository, exerciseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
