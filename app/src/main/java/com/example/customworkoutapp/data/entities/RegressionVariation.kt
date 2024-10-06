package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regression_variation")
data class RegressionVariation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String  // Regression variation (e.g., "Bodyweight version", "Decrease intensity")
)
