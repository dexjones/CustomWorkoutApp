package com.example.customworkoutapp.data.repository

import androidx.lifecycle.LiveData
import com.example.customworkoutapp.data.dao.ExerciseDao
import com.example.customworkoutapp.data.entities.Exercise
import com.example.customworkoutapp.data.entities.ExerciseWithEquipmentDetails

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

    // Get exercises filtered by muscle group and equipment
    suspend fun getExercisesByMuscleGroupAndEquipment(muscleGroupId: Int, equipmentIds: List<Int>): List<ExerciseWithEquipmentDetails> {
        return exerciseDao.getExercisesForMuscleGroupAndEquipment(muscleGroupId, equipmentIds)
    }

    // Get equipment ID by name
    suspend fun getEquipmentIdByName(equipmentName: String): Int? {
        return exerciseDao.getEquipmentIdByName(equipmentName)
    }
}
