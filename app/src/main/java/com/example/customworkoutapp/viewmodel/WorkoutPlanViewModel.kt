package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.dao.WorkoutPlanDao
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(application: Application) : AndroidViewModel(application) {
    private val workoutPlanDao: WorkoutPlanDao = AppDatabase.getDatabase(application).workoutPlanDao()

    fun getWorkoutPlans(userId: Int): LiveData<List<WorkoutPlan>> {
        return workoutPlanDao.getWorkoutPlansByUser(userId)
    }

    fun insertWorkoutPlan(workoutPlan: WorkoutPlan) {
        viewModelScope.launch {
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }
}