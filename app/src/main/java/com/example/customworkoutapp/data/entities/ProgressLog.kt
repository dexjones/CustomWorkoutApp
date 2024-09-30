package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress_logs")
data class ProgressLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val date: Long, // Store as a timestamp
    val weight: Float? = null, // Current body weight
    val bodyFatPercentage: Float? = null,
    val workoutDuration: Int? = null, // in minutes
    val caloriesBurned: Int? = null
)

