package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ExerciseMuscleGroup
import com.example.customworkoutapp.data.entities.ExerciseWithMuscleGroup

@Dao
interface ExerciseMuscleGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseMuscleGroup(exerciseMuscleGroup: ExerciseMuscleGroup)

    @Query("SELECT * FROM exercise_muscle_group WHERE exercise_id = :exerciseId")
    suspend fun getMuscleGroupsForExercise(exerciseId: Int): List<ExerciseMuscleGroup>

    @Query("""
    SELECT exercise.name AS exerciseName, muscle_group.name AS muscleGroupName
    FROM exercise
    INNER JOIN exercise_muscle_group ON exercise.id = exercise_muscle_group.exercise_id
    INNER JOIN muscle_group ON exercise_muscle_group.muscle_group_id = muscle_group.id
    WHERE exercise_muscle_group.exercise_id = :exerciseId
""")
    fun getExerciseWithMuscleGroup(exerciseId: Int): List<ExerciseWithMuscleGroup>
}
