package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import com.example.customworkoutapp.data.dao.ExerciseDao
import com.example.customworkoutapp.data.entities.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    suspend fun getExerciseById(id: Int): Exercise? {
        return exerciseDao.getExerciseById(id)
    }

    fun getAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    suspend fun deleteExerciseById(id: Int) {
        exerciseDao.deleteExerciseById(id)
    }
}
