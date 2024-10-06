package com.example.customworkoutapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customworkoutapp.data.entities.ExerciseWithSets
import com.example.customworkoutapp.data.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutLogViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {

    // Fetch exercises for a specific workout (by workoutId)
    fun getWorkoutExercises(workoutId: Int): LiveData<List<ExerciseWithSets>> {
        return workoutRepository.getExercisesByWorkoutId(workoutId)
    }

    // Submit updated workout data
    fun submitWorkout(updatedExercises: List<ExerciseWithSets>) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.updateWorkoutExercises(updatedExercises)
        }
    }
}
