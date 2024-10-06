package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_type",
    primaryKeys = ["exercise_id", "type_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = Type::class, parentColumns = ["id"], childColumns = ["type_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["type_id"])]
)
data class ExerciseType(
    val exercise_id: Int,
    val type_id: Int
)
