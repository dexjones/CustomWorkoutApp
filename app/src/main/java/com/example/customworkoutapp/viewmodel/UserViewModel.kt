package com.example.customworkoutapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.User
import com.example.customworkoutapp.data.entities.Goal
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.repository.GoalRepository
import com.example.customworkoutapp.data.repository.UserRepository
import com.example.customworkoutapp.data.repository.WorkoutPlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private val goalRepository: GoalRepository
    private val workoutPlanRepository: WorkoutPlanRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        val goalDao = AppDatabase.getDatabase(application).goalDao()
        val workoutPlanDao = AppDatabase.getDatabase(application).workoutPlanDao()
        userRepository = UserRepository(userDao)
        goalRepository = GoalRepository(goalDao)
        workoutPlanRepository = WorkoutPlanRepository(workoutPlanDao)
    }

    // Fetch user by email
    suspend fun getUserByEmail(email: String): User? {
        return userRepository.getUserByEmail(email)
    }

    // Insert or update user
    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    // Insert a goal using GoalRepository
    suspend fun insertGoal(goal: Goal): Long {
        return goalRepository.insertGoal(goal)
    }

    // Correct the insertWorkoutPlans method to use WorkoutPlanRepository
    fun insertWorkoutPlans(workoutPlans: List<WorkoutPlan>) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutPlanRepository.insertWorkoutPlans(workoutPlans)
        }
    }

    // Insert goal and workout plans together
    fun insertGoalAndWorkoutPlans(goal: Goal, workoutPlans: List<WorkoutPlan>) {
        viewModelScope.launch {
            val goalId = goalRepository.insertGoal(goal)

            // Update workout plans with the goalId
            workoutPlans.forEach { workoutPlan ->
                workoutPlan.goalId = goalId.toInt()
                workoutPlanRepository.insertWorkoutPlan(workoutPlan)
            }
        }
    }
}
