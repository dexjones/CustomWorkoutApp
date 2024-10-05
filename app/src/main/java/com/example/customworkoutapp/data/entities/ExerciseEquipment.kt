package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_equipment",
    primaryKeys = ["exercise_id", "equipment_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = Equipment::class, parentColumns = ["id"], childColumns = ["equipment_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["equipment_id"])]
)
data class ExerciseEquipment(
    val exercise_id: Int,
    val equipment_id: Int,
    val is_optional: Boolean? = null // Boolean value for optional equipment
)
