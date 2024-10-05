package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseRegressionVariation
import com.example.customworkoutapp.data.entities.ExerciseWithProgressionVariation
import com.example.customworkoutapp.data.entities.ExerciseWithRegressionVariation

@Dao
interface ExerciseRegressionVariationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseRegressionVariation(exerciseRegressionVariation: ExerciseRegressionVariation)

    @Query("SELECT * FROM exercise_regression_variation WHERE exercise_id = :exerciseId")
    suspend fun getRegressionVariationsForExercise(exerciseId: Int): List<ExerciseRegressionVariation>

    @Query("""
        SELECT exercise.name AS exerciseName, progression_variation.name AS progressionVariationName
        FROM exercise
        INNER JOIN exercise_progression_variation ON exercise.id = exercise_progression_variation.exercise_id
        INNER JOIN progression_variation ON exercise_progression_variation.progression_variation_id = progression_variation.id
        WHERE exercise_progression_variation.exercise_id = :exerciseId
    """)
    fun getExerciseWithProgressionVariations(exerciseId: Int): List<ExerciseWithProgressionVariation>
}
