package com.example.customworkoutapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.Exercise
import com.example.customworkoutapp.data.entities.ExerciseWithEquipmentDetails

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExerciseById(id: Int)

    @Query("SELECT id FROM equipment WHERE name = :equipmentName LIMIT 1")
    suspend fun getEquipmentIdByName(equipmentName: String): Int?

    @Query("""
    SELECT e.name as exerciseName, eq.name as equipmentName, ee.is_optional as isOptional
    FROM exercise e
    INNER JOIN exercise_muscle_group emg ON e.id = emg.exercise_id
    INNER JOIN exercise_equipment ee ON e.id = ee.exercise_id
    INNER JOIN equipment eq ON ee.equipment_id = eq.id
    WHERE emg.muscle_group_id = :muscleGroupId AND ee.equipment_id IN (:equipmentIds)
""")
    suspend fun getExercisesForMuscleGroupAndEquipment(muscleGroupId: Int, equipmentIds: List<Int>): List<ExerciseWithEquipmentDetails>

    @Query("""
    SELECT e.name as exerciseName, eq.name as equipmentName, ee.is_optional as isOptional
    FROM exercise e
    INNER JOIN exercise_equipment ee ON e.id = ee.exercise_id
    INNER JOIN equipment eq ON ee.equipment_id = eq.id
    WHERE ee.exercise_id = :exerciseId
""")
    suspend fun getExerciseWithEquipmentDetails(exerciseId: Int): List<ExerciseWithEquipmentDetails>

    @Query("""
    SELECT e.name as exerciseName, eq.name as equipmentName, ee.is_optional as isOptional
    FROM exercise e
    INNER JOIN exercise_equipment ee ON e.id = ee.exercise_id
    INNER JOIN equipment eq ON ee.equipment_id = eq.id
    WHERE ee.equipment_id IN (:equipmentIds)
""")
    suspend fun getExercisesForEquipment(equipmentIds: List<Int>): List<ExerciseWithEquipmentDetails>

}