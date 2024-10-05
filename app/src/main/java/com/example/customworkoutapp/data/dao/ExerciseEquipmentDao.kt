package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseEquipment
import com.example.customworkoutapp.data.entities.ExerciseWithEquipment

@Dao
interface ExerciseEquipmentDao {
    // Insert exercise equipment with is_optional flag
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseEquipment(exerciseEquipment: ExerciseEquipment)

    // Get all equipment required for a particular exercise
    @Query("SELECT * FROM exercise_equipment WHERE exercise_id = :exerciseId")
    suspend fun getEquipmentForExercise(exerciseId: Int): List<ExerciseEquipment>

    // Fetch exercise with its equipment and check whether it’s optional or not
    @Query("""
   SELECT exercise.name AS exerciseName, equipment.name AS equipmentName, exercise_equipment.is_optional AS isOptional
   FROM exercise
   INNER JOIN exercise_equipment ON exercise.id = exercise_equipment.exercise_id
   INNER JOIN equipment ON exercise_equipment.equipment_id = equipment.id
   WHERE exercise_equipment.exercise_id = :exerciseId
""")
    suspend fun getExerciseWithEquipment(exerciseId: Int): List<ExerciseWithEquipment>
}