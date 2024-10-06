package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_mode")
data class TrainingMode(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String  // Training mode (e.g., "Strength", "Endurance")
)
