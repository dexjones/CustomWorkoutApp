package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.Exercise
import com.example.customworkoutapp.data.dao.ExerciseDao
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseDao: ExerciseDao = AppDatabase.getDatabase(application).exerciseDao()

    fun getAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.insertExercise(exercise)
        }
    }
}