package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customworkoutapp.data.entities.Goal
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.repository.GoalRepository
import kotlinx.coroutines.Dispatchers

class GoalViewModel(private val goalRepository: GoalRepository) : ViewModel(){

    val intent = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val equipment = MutableLiveData<List<String>>()
    val experience = MutableLiveData<String>()
    val duration = MutableLiveData<Int>()
    val frequency = MutableLiveData<Int>()



    fun updateIntent(intent: String) {
        this.intent.value = intent
    }
    fun updateLocation(location: String) {
        this.location.value = location
    }

    fun updateEquipment(equipment: List<String>) {
        this.equipment.value = equipment
    }

    fun updateExperience(experience: String) {
        this.experience.value = experience
    }

    fun updateDuration(duration: Int) {
        this.duration.value = duration
    }

    fun updateFrequency(frequency: Int) {
        this.frequency.value = frequency
    }
    fun createGoal(): Goal {
        return Goal(
            intent = this.intent.value ?: "",  // Accessing LiveData's value
            location = this.location.value ?: "",
            equipment = this.equipment.value?.joinToString(",") ?: "",
            experience = this.experience.value ?: "",
            duration = this.duration.value ?: 0,
            frequency = this.frequency.value ?: 0
        )
    }


    fun generateWorkoutPlans(userId: Int, goalId: Int): List<WorkoutPlan> {
        // Get information from the goal and generate a workout plan
        val workoutPlans = mutableListOf<WorkoutPlan>()

        val frequencyValue = frequency.value ?: 3  // Use a default frequency of 3 if null

        // Example logic for generating workout plans based on frequency
        for (i in 1..(frequency.value ?: 3)) {
            val workoutPlan = WorkoutPlan(
                goalId = goalId,
                exerciseName = "Exercise ${i}",  // Example placeholder for exercise
                sets = 3,                        // Default number of sets
                reps = 10,                       // Default number of reps
                weight = null,                   // Placeholder for weight
                userId = userId                  // Link to userId
            )
            workoutPlans.add(workoutPlan)
        }

        return workoutPlans
    }
}
