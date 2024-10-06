package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_progression_variation",
    primaryKeys = ["exercise_id", "progression_variation_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = ProgressionVariation::class, parentColumns = ["id"], childColumns = ["progression_variation_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["progression_variation_id"])]
)
data class ExerciseProgressionVariation(
    val exercise_id: Int,
    val progression_variation_id: Int
)
