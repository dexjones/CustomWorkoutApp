package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import com.example.customworkoutapp.data.dao.WorkoutDao
import com.example.customworkoutapp.data.entities.ExerciseWithSets

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    // Fetch exercises for a specific workout (by workoutId)
    fun getExercisesByWorkoutId(workoutId: Int): LiveData<List<ExerciseWithSets>> {
        return workoutDao.getExercisesByWorkoutId(workoutId)
    }

    // Update workout exercises
    suspend fun updateWorkoutExercises(exerciseList: List<ExerciseWithSets>) {
        workoutDao.updateWorkoutExercises(exerciseList)
    }
}
