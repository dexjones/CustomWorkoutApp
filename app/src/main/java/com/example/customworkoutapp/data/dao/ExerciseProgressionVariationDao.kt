package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseProgressionVariation
import com.example.customworkoutapp.data.entities.ExerciseWithProgressionVariation

@Dao
interface ExerciseProgressionVariationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseProgressionVariation(exerciseProgressionVariation: ExerciseProgressionVariation)

    @Query("SELECT * FROM exercise_progression_variation WHERE exercise_id = :exerciseId")
    suspend fun getProgressionVariationsForExercise(exerciseId: Int): List<ExerciseProgressionVariation>

    @Query("""
    SELECT exercise.name AS exerciseName, progression_variation.name AS progressionVariationName
    FROM exercise
    INNER JOIN exercise_progression_variation ON exercise.id = exercise_progression_variation.exercise_id
    INNER JOIN progression_variation ON exercise_progression_variation.progression_variation_id = progression_variation.id
    WHERE exercise_progression_variation.exercise_id = :exerciseId
""")
    fun getExerciseWithProgressionVariations(exerciseId: Int): List<ExerciseWithProgressionVariation>
}
