package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String? = null,
    val experienceLevel: String, // Exercise Experience Level (e.g., Beginner, Intermediate, Advanced)
    val duration: Int? = null, // Exercise Duration (in seconds)
    val intensity: String, // Exercise Intensity (e.g., Low, Medium, High)
    val movementType: String, // Movement Type (e.g., Compound, Isolation)
    val restTime: Int? = null, // Rest/Recovery Time (in seconds)
    val maxFrequency: Int,
    val videoURL: String? = null
)
