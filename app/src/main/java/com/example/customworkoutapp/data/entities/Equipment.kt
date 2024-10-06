package com.example.customworkoutapp.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String  // Equipment name (e.g., "Dumbbells", "Barbell")
)
