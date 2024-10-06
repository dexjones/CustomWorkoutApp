package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Update
import com.example.customworkoutapp.data.entities.ExerciseWithSets
import com.example.customworkoutapp.data.entities.Workout

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Query("SELECT * FROM workouts WHERE userId = :userId")
    fun getWorkoutPlan(userId: Int): LiveData<List<Workout>>

    @Query("SELECT * FROM workouts WHERE userId = :userId")
    fun getWorkoutsByUser(userId: Int): LiveData<List<Workout>>

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
    fun getExercisesByWorkoutId(workoutId: Int): LiveData<List<ExerciseWithSets>>

    @Query("SELECT * FROM exercises WHERE userId = :userId")
    fun getExercisesByUserId(userId: Int): LiveData<List<ExerciseWithSets>>


    // Update exercise data for a workout
    @Update
    suspend fun updateWorkoutExercises(exerciseList: List<ExerciseWithSets>)
}