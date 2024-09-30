package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String, // e.g., "strength", "cardio", "HIIT"
    val muscleGroup: String, // e.g., "legs", "arms"
    val equipment: String, // e.g., "dumbbells", "none"
    val description: String,
    val videoUrl: String? = null
)
