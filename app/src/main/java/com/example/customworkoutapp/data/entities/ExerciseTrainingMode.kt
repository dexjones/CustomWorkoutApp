package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_training_mode",
    primaryKeys = ["exercise_id", "training_mode_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = TrainingMode::class, parentColumns = ["id"], childColumns = ["training_mode_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["training_mode_id"])]
)
data class ExerciseTrainingMode(
    val exercise_id: Int,
    val training_mode_id: Int
)
