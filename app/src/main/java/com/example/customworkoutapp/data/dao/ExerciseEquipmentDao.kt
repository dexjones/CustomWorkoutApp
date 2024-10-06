package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseEquipment

@Dao
interface ExerciseEquipmentDao {
    // Insert exercise equipment with is_optional flag
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseEquipment(exerciseEquipment: ExerciseEquipment)

    // Get all equipment required for a particular exercise
    @Query("SELECT * FROM exercise_equipment WHERE exercise_id = :exerciseId")
    suspend fun getEquipmentForExercise(exerciseId: Int): List<ExerciseEquipment>

}