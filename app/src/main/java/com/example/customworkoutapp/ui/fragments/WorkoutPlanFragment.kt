package com.example.customworkoutapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customworkoutapp.R
import com.example.customworkoutapp.ui.adapters.WorkoutPlanAdapter
import com.example.customworkoutapp.viewmodel.WorkoutPlanViewModel
import com.example.customworkoutapp.viewmodel.WorkoutPlanViewModelFactory
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.repository.WorkoutPlanRepository

class WorkoutPlanFragment : Fragment() {

    private lateinit var workoutPlanAdapter: WorkoutPlanAdapter
    private lateinit var workoutPlanViewModel: WorkoutPlanViewModel
    private lateinit var workoutPlanRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutPlanRecyclerView = view.findViewById(R.id.workout_plan_recyclerview)

        workoutPlanAdapter = WorkoutPlanAdapter(emptyList()) // Initialize with empty list
        workoutPlanRecyclerView.layoutManager = LinearLayoutManager(context)
        workoutPlanRecyclerView.adapter = workoutPlanAdapter

        // Initialize ViewModel with WorkoutPlanRepository using the ViewModelFactory
        val workoutPlanDao = AppDatabase.getDatabase(requireContext()).workoutPlanDao()
        val workoutPlanRepository = WorkoutPlanRepository(workoutPlanDao)
        val workoutPlanViewModelFactory = WorkoutPlanViewModelFactory(workoutPlanRepository)

        workoutPlanViewModel = ViewModelProvider(this, workoutPlanViewModelFactory).get(WorkoutPlanViewModel::class.java)

        // Retrieve the userId dynamically from SharedPreferences as a String
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userIdString = sharedPreferences.getString("userId", null)

        // Convert the userId to an Int, or use -1 if it's null or not a valid number
        val userId = userIdString?.toIntOrNull() ?: -1

        if (userId != -1) {
            workoutPlanViewModel.getWorkoutPlansForUser(userId).observe(viewLifecycleOwner) { workoutPlans ->
                workoutPlans?.let {
                    workoutPlanAdapter.setWorkouts(it)
                }
            }
        }
    }
}
