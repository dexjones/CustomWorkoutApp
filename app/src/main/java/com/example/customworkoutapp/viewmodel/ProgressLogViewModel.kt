package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.ProgressLog
import com.example.customworkoutapp.data.dao.ProgressLogDao
import kotlinx.coroutines.launch

class ProgressLogViewModel(application: Application) : AndroidViewModel(application) {
    private val progressLogDao: ProgressLogDao = AppDatabase.getDatabase(application).progressLogDao()

    fun getProgressLogs(userId: Int): LiveData<List<ProgressLog>> {
        return progressLogDao.getProgressLogsByUser(userId)
    }

    fun insertProgressLog(log: ProgressLog) {
        viewModelScope.launch {
            progressLogDao.insertProgressLog(log)
        }
    }
}