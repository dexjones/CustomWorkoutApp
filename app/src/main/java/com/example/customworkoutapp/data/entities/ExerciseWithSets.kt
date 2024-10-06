package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.example.customworkoutapp.data.entities.User
import com.example.customworkoutapp.data.entities.Workout

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseWithSets(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                      // Primary key for the exercise

    @ColumnInfo(index = true)
    val workoutId: Int,                   // Foreign key to link with the workouts table

    @ColumnInfo(index = true)
    val userId: Int,                      // Foreign key to link with the users table

    val exerciseName: String,             // Name of the exercise

    val sets: Int,                        // Number of sets

    val reps: Int,                        // Number of repetitions per set

    val weight: Float                     // Weight used in the exercise
)
