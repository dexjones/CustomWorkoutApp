package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val intent: String, // "Custom workout" or "Create from scratch"
    val location: String, // "Home" or "Gym"
    val equipment: String, // Available equipment
    val experience: String, // "Beginner", "Intermediate", "Advanced"
    val duration: Int, // Duration in minutes (30, 45, 60)
    val frequency: Int // Frequency of workouts per week (3-6)
)
