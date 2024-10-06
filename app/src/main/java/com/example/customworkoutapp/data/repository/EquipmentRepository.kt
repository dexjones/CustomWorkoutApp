package com.example.customworkoutapp.data.repository

import com.example.customworkoutapp.data.dao.EquipmentDao
import com.example.customworkoutapp.data.entities.Equipment

class EquipmentRepository(private val equipmentDao: EquipmentDao) {

    // Function to insert a new equipment
    suspend fun insertEquipment(equipment: Equipment) {
        equipmentDao.insertEquipment(equipment)
    }

    // Function to fetch all equipment
    suspend fun getAllEquipment(): List<Equipment> {
        return equipmentDao.getAllEquipment()
    }

    // Function to delete an equipment item
    suspend fun deleteEquipment(equipment: Equipment) {
        equipmentDao.deleteEquipment(equipment)
    }

    // Function to update an equipment item
    suspend fun updateEquipment(equipment: Equipment) {
        equipmentDao.updateEquipment(equipment)
    }
}
