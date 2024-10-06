package com.example.customworkoutapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "exercise_muscle_group",
    primaryKeys = ["exercise_id", "muscle_group_id"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"]),
        ForeignKey(entity = MuscleGroup::class, parentColumns = ["id"], childColumns = ["muscle_group_id"])
    ],
    indices = [Index(value = ["exercise_id"]), Index(value = ["muscle_group_id"])]
)
data class ExerciseMuscleGroup(
    val exercise_id: Int,
    val muscle_group_id: Int
)
