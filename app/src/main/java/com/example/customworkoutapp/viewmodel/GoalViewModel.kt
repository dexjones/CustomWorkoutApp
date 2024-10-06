package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customworkoutapp.data.entities.ExerciseWithEquipmentDetails
import com.example.customworkoutapp.data.entities.Goal
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.repository.ExerciseRepository
import com.example.customworkoutapp.data.repository.GoalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GoalViewModel(
    private val goalRepository: GoalRepository,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    // MutableLiveData for Goal properties
    val intent = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val equipment = MutableLiveData<List<String>>()
    val experience = MutableLiveData<String>()
    val duration = MutableLiveData<Int>()
    val frequency = MutableLiveData<Int>()

    // Update methods to set Goal properties
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

    // Create a Goal object with the passed userId
    fun createGoal(userId: Int): Goal {
        return Goal(
            userId = userId,
            intent = this.intent.value ?: "",
            location = this.location.value ?: "",
            equipment = this.equipment.value?.joinToString(",") ?: "",
            experience = this.experience.value ?: "",
            duration = this.duration.value ?: 0,
            frequency = this.frequency.value ?: 0
        )
    }

    // Fetch the goal for a specific user
    fun getGoalForUser(userId: Int): LiveData<Goal> {
        return goalRepository.getGoalForUser(userId)
    }

    // Fetch exercises by muscle group and equipment
    private suspend fun getExercisesForMuscleGroupAndEquipment(muscleGroupId: Int, availableEquipment: List<Int>): List<ExerciseWithEquipmentDetails> {
        return withContext(Dispatchers.IO) {


            exerciseRepository.getExercisesByMuscleGroupAndEquipment(muscleGroupId, availableEquipment)
        }
    }

    // Generate workout plans based on the user's goal, muscle groups, and equipment
    suspend fun generateWorkoutPlans(userId: Int, goalId: Int, goal: Goal): List<WorkoutPlan> {
        val workoutPlans = mutableListOf<WorkoutPlan>()
        val frequencyValue = goal.frequency
        val availableEquipment = goal.equipment.split(",")
        val muscleGroups = listOf(
            Pair("Chest", 1),
            Pair("Back", listOf(2,15)),
            Pair("Shoulders", 3),
            Pair("Arms", listOf(4, 5, 6)),
            Pair("Legs", listOf(9, 10, 11, 12, 13, 14))
        )
        val availableEquipmentIds = availableEquipment.mapNotNull { equipmentNameOrId ->
            try {
                equipmentNameOrId.toInt()  // Try to parse it as an ID
            } catch (e: NumberFormatException) {
                // Lookup the ID from the database if it's a name
                exerciseRepository.getEquipmentIdByName(equipmentNameOrId.trim())
            }
        }
        // Determine sets and reps based on the user's experience level
        val (sets, reps) = when (goal.experience) {
            "Beginner" -> 3 to 16
            "Intermediate", "Advanced" -> 4 to 12
            else -> 3 to 10
        }

        // Generate workout plans for 3-6 days based on muscle groups and equipment
        when (frequencyValue) {
            3 -> {
                val muscleGroupIds: List<Int> = listOf(1, 2, 9) // Chest, Back, Legs
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Chest Day", muscleGroupIds[0], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Back Day", muscleGroupIds[1], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Leg Day", muscleGroupIds[2], sets, reps, availableEquipmentIds))
            }
            4 -> {
                val muscleGroupIds: List<Int> = listOf(1, 9, 2, 7) // Chest, Legs, Back, Core
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Chest Day", muscleGroupIds[0], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Leg Day", muscleGroupIds[1], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Back Day", muscleGroupIds[2], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Core Day", muscleGroupIds[3], sets, reps, availableEquipmentIds))
            }
            5 -> {
                val muscleGroupIds: List<Int> = listOf(1, 2, 3, 9, 4) // Chest, Back, Shoulders, Legs, Arms
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Chest Day", muscleGroupIds[0], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Back Day", muscleGroupIds[1], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Shoulder Day", muscleGroupIds[2], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Leg Day", muscleGroupIds[3], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Arm Day", muscleGroupIds[4], sets, reps, availableEquipmentIds))
            }
            6 -> {
                val muscleGroupIds: List<Int> = listOf(1, 2, 3, 9, 4, 7) // Chest, Back, Shoulders, Legs, Arms, Core
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Chest Day", muscleGroupIds[0], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Back Day", muscleGroupIds[1], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Shoulder Day", muscleGroupIds[2], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Leg Day", muscleGroupIds[3], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Arm Day", muscleGroupIds[4], sets, reps, availableEquipmentIds))
                workoutPlans.addAll(generateDayWorkout(userId, goalId, "Core Day", muscleGroupIds[5], sets, reps, availableEquipmentIds))
            }
        }
        return workoutPlans
    }

    // Helper function to generate workout plans for a specific day and muscle group
    private suspend fun generateDayWorkout(
        userId: Int,
        goalId: Int,
        dayName: String,
        muscleGroupId: Int,
        sets: Int,
        reps: Int,
        availableEquipmentIds: List<Int>
    ): List<WorkoutPlan> {
        val exercises = getExercisesForMuscleGroupAndEquipment(muscleGroupId, availableEquipmentIds)
        val workoutPlans = mutableListOf<WorkoutPlan>()

        exercises.forEach { exerciseWithEquipment ->
            workoutPlans.add(
                WorkoutPlan(
                    goalId = goalId,
                    exerciseName = exerciseWithEquipment.exerciseName,
                    sets = sets,
                    reps = reps,
                    weight = null,
                    userId = userId
                )
            )
        }
        return workoutPlans
    }
}
