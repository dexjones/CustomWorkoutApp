package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progression_variation")
data class ProgressionVariation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String  // Progression variation (e.g., "Add weight", "Increase intensity")
)
