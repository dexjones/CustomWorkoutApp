package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_plans")
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // Foreign key linking to the User entity
    val planName: String,
    val durationWeeks: Int,
    val goal: String, // e.g., "build muscle", "lose weight"
    val creationDate: Long = System.currentTimeMillis()
)