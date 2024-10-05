package com.example.customworkoutapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.customworkoutapp.data.entities.Equipment

@Dao
interface EquipmentDao {

    // Insert a single equipment item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipment(equipment: Equipment)

    // Insert multiple equipment items at once
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEquipment(equipmentList: List<Equipment>)

    // Query to get all equipment
    @Query("SELECT * FROM equipment")
    suspend fun getAllEquipment(): List<Equipment>

    // Query to get equipment by ID
    @Query("SELECT * FROM equipment WHERE id = :equipmentId")
    suspend fun getEquipmentById(equipmentId: Int): Equipment?

    // Delete a specific equipment by ID
    @Delete
    suspend fun deleteEquipment(equipment: Equipment)

    // Update an existing equipment
    @Update
    suspend fun updateEquipment(equipment: Equipment)
}
