package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.data.repository.GoalRepository

class GoalViewModelFactory(private val goalRepository: GoalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GoalViewModel(goalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

