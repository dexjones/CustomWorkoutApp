package com.example.customworkoutapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.Exercise

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
}