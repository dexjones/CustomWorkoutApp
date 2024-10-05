package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseTrainingMode
import com.example.customworkoutapp.data.entities.ExerciseWithTrainingMode

@Dao
interface ExerciseTrainingModeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseTrainingMode(exerciseTrainingMode: ExerciseTrainingMode)

    @Query("SELECT * FROM exercise_training_mode WHERE exercise_id = :exerciseId")
    suspend fun getTrainingModesForExercise(exerciseId: Int): List<ExerciseTrainingMode>

    @Query("""
    SELECT exercise.name AS exerciseName, training_mode.name AS trainingModeName
    FROM exercise
    INNER JOIN exercise_training_mode ON exercise.id = exercise_training_mode.exercise_id
    INNER JOIN training_mode ON exercise_training_mode.training_mode_id = training_mode.id
""")
    fun getExerciseWithTrainingModes(): List<ExerciseWithTrainingMode>

}
