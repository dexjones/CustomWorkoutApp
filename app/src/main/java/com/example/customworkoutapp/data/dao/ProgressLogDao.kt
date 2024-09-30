package com.example.customworkoutapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.customworkoutapp.data.entities.ProgressLog

@Dao
interface ProgressLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgressLog(log: ProgressLog)

    @Query("SELECT * FROM progress_logs WHERE userId = :userId")
    fun getProgressLogsByUser(userId: Int): LiveData<List<ProgressLog>>
}