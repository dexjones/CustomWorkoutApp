package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_regression_variation",
    primaryKeys = ["exercise_id", "regression_variation_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = RegressionVariation::class, parentColumns = ["id"], childColumns = ["regression_variation_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["regression_variation_id"])]
)
data class ExerciseRegressionVariation(
    val exercise_id: Int,
    val regression_variation_id: Int
)
