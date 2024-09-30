package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.Workout
import com.example.customworkoutapp.data.dao.WorkoutDao
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val workoutDao: WorkoutDao = AppDatabase.getDatabase(application).workoutDao()

    fun getWorkouts(userId: Int): LiveData<List<Workout>> {
        return workoutDao.getWorkoutsByUser(userId)
    }

    fun insertWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutDao.insertWorkout(workout)
        }
    }
}