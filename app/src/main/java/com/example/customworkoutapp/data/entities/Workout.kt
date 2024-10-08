package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "workouts", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["workoutPlanId"], onDelete = ForeignKey.CASCADE)])
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val workoutPlanId: Int, // Link to workout plan
    val exerciseName: String,
    val sets: Int,
    val reps: Int,
    val weight: Float? = null
)