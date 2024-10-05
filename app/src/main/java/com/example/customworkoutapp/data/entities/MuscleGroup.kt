package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle_group")
data class MuscleGroup(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String  // Muscle Group name (e.g., "Quadriceps", "Hamstrings")
)
