package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "workout_plan",
    foreignKeys = [
        ForeignKey(entity = Goal::class, parentColumns = ["id"], childColumns = ["goalId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var goalId: Int,
    val exerciseName: String,
    val sets: Int,
    val reps: Int,
    val weight: Float?,
    val userId: Int
)

data class WorkoutPlanWithWorkouts(
    @Relation(parentColumn = "id", entityColumn = "workoutPlanId")
    val workouts: List<Workout>
)
