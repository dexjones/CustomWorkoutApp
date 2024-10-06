package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // Foreign key to the user's table
    val intent: String, // "Custom workout" or "Create from scratch"
    val location: String, // "Home" or "Gym"
    val equipment: String, // Available equipment
    val experience: String, // "Beginner", "Intermediate", "Advanced"
    val duration: Int, // Duration in minutes (30, 45, 60)
    val frequency: Int // Frequency of workouts per week (3-6)
)
