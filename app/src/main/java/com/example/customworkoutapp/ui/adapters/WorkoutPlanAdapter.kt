package com.example.customworkoutapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.entities.WorkoutPlan

class WorkoutPlanAdapter(private var workoutList: List<WorkoutPlan>) :
    RecyclerView.Adapter<WorkoutPlanAdapter.WorkoutViewHolder>() {

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName: TextView = itemView.findViewById(R.id.tv_workout_name)
        val setsReps: TextView = itemView.findViewById(R.id.tv_sets_reps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workoutList[position]
        holder.workoutName.text = workout.exerciseName
        holder.setsReps.text = "${workout.sets} sets x ${workout.reps} reps"

        holder.itemView.setOnClickListener {
            // Handle navigation to detailed workout tracking page
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    // Function to update the list when the data changes
    fun setWorkouts(workoutPlans: List<WorkoutPlan>) {
        this.workoutList = workoutPlans
        notifyDataSetChanged()  // Refresh the RecyclerView with new data
    }
}
