package com.example.customworkoutapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customworkoutapp.R
import com.example.customworkoutapp.ui.adapters.WorkoutPlanAdapter
import com.example.customworkoutapp.viewmodel.GoalViewModel
import com.example.customworkoutapp.viewmodel.WorkoutPlanViewModel
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.WorkoutPlan
import com.example.customworkoutapp.data.repository.ExerciseRepository
import com.example.customworkoutapp.data.repository.GoalRepository
import com.example.customworkoutapp.data.repository.WorkoutPlanRepository
import com.example.customworkoutapp.viewmodel.WorkoutPlanViewModelFactory
import com.example.customworkoutapp.viewmodel.GoalViewModelFactory
import kotlinx.coroutines.launch

class WorkoutPlanFragment : Fragment() {

    private lateinit var workoutPlanAdapter: WorkoutPlanAdapter
    private lateinit var workoutPlanViewModel: WorkoutPlanViewModel
    private lateinit var workoutPlanRecyclerView: RecyclerView
    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutPlanRecyclerView = view.findViewById(R.id.workout_plan_recyclerview)
        workoutPlanRecyclerView.layoutManager = LinearLayoutManager(context)


        workoutPlanAdapter = WorkoutPlanAdapter(emptyList()) // Initialize with empty list
        workoutPlanRecyclerView.adapter = workoutPlanAdapter

        // Initialize ViewModel
        val workoutPlanDao = AppDatabase.getDatabase(requireContext()).workoutPlanDao()
        val workoutPlanRepository = WorkoutPlanRepository(workoutPlanDao)
        val workoutPlanViewModelFactory = WorkoutPlanViewModelFactory(workoutPlanRepository)

        val goalDao = AppDatabase.getDatabase(requireContext()).goalDao()
        val goalRepository = GoalRepository(goalDao)

        val exerciseDao = AppDatabase.getDatabase(requireContext()).exerciseDao()
        val exerciseRepository = ExerciseRepository(exerciseDao)

        workoutPlanViewModel = ViewModelProvider(this, workoutPlanViewModelFactory).get(WorkoutPlanViewModel::class.java)

        val goalViewModelFactory = GoalViewModelFactory(goalRepository, exerciseRepository)
        goalViewModel = ViewModelProvider(this, goalViewModelFactory).get(GoalViewModel::class.java)

        workoutPlanAdapter = WorkoutPlanAdapter(emptyList())
        val recyclerView = view.findViewById<RecyclerView>(R.id.workout_plan_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = workoutPlanAdapter

        // Retrieve the userId dynamically from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userIdString = sharedPreferences.getString("userId", null)
        val userId = userIdString?.toIntOrNull() ?: -1

        if (userId != -1) {
            // In WorkoutPlanFragment, update onViewCreated to observe goal and generate workout plans
            goalViewModel.getGoalForUser(userId).observe(viewLifecycleOwner) { goal ->
                goal?.let {
                    // Use lifecycleScope to handle suspend functions
                    viewLifecycleOwner.lifecycleScope.launch {
                        // Generate workout plans and update the adapter
                        val workoutPlans = goalViewModel.generateWorkoutPlans(
                            userId = userId,
                            goalId = it.id,
                            goal = it
                        )
                        workoutPlanAdapter.setWorkouts(workoutPlans)
                    }
                }
            }

        }
    }
}
