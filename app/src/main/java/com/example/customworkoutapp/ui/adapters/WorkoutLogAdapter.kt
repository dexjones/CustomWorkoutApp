package com.example.customworkoutapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.entities.ExerciseWithSets

class WorkoutLogAdapter(private var exerciseList: List<ExerciseWithSets> = emptyList()) :
    RecyclerView.Adapter<WorkoutLogAdapter.WorkoutLogViewHolder>() {

    // ViewHolder class to bind each item in the list
    inner class WorkoutLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.tv_exercise_name)
        val setsInput: EditText = itemView.findViewById(R.id.et_sets)
        val repsInput: EditText = itemView.findViewById(R.id.et_reps)
        val weightInput: EditText = itemView.findViewById(R.id.et_weight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutLogViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_log, parent, false)
        return WorkoutLogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutLogViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.exerciseName.text = exercise.exerciseName

        // Set default values for sets, reps, and weight
        holder.setsInput.setText(exercise.sets.toString())
        holder.repsInput.setText(exercise.reps.toString())
        holder.weightInput.setText(exercise.weight.toString())
    }

    override fun getItemCount(): Int = exerciseList.size

    // Method to update the adapter's data
    fun setExercises(newExercises: List<ExerciseWithSets>) {
        exerciseList = newExercises
        notifyDataSetChanged()  // Notify adapter about data changes
    }

    // Method to get updated data from EditText inputs
    fun getUpdatedData(): List<ExerciseWithSets> {
        return exerciseList.mapIndexed { index, exercise ->
            exercise.copy(
                sets = exercise.sets,   // Update with the latest value
                reps = exercise.reps,   // Update with the latest value
                weight = exercise.weight  // Update with the latest value
            )
        }
    }
}
