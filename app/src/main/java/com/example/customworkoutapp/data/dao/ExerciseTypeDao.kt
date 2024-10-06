package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseType
import com.example.customworkoutapp.data.entities.ExerciseWithType

@Dao
interface ExerciseTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseType(exerciseType: ExerciseType)

    @Query("SELECT * FROM exercise_type WHERE exercise_id = :exerciseId")
    suspend fun getTypesForExercise(exerciseId: Int): List<ExerciseType>

    @Query("""
    SELECT exercise.name AS exerciseName, type.name AS typeName
    FROM exercise
    INNER JOIN exercise_type ON exercise.id = exercise_type.exercise_id
    INNER JOIN type ON exercise_type.type_id = type.id
""")
    fun getExerciseWithTypes(): List<ExerciseWithType>

}
