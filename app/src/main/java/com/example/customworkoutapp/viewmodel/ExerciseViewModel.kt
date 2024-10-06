package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.Exercise
import com.example.customworkoutapp.data.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExerciseRepository
    val allExercises: LiveData<List<Exercise>>

    init {
        val exerciseDao = AppDatabase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(exerciseDao)
        allExercises = repository.getAllExercises()
    }

    fun insert(exercise: Exercise) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertExercise(exercise)
    }

    fun deleteExerciseById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteExerciseById(id)
    }

    suspend fun getExerciseById(id: Int): Exercise? {
        return repository.getExerciseById(id)
    }
}
