package com.example.customworkoutapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.ui.adapters.WorkoutLogAdapter
import com.example.customworkoutapp.viewmodel.WorkoutLogViewModel
import com.example.customworkoutapp.viewmodel.WorkoutLogViewModelFactory
import com.example.customworkoutapp.data.entities.ExerciseWithSets
import com.example.customworkoutapp.data.repository.WorkoutRepository

class WorkoutLogFragment : Fragment() {

    private lateinit var workoutLogViewModel: WorkoutLogViewModel
    private lateinit var workoutLogAdapter: WorkoutLogAdapter
    private var workoutId: Int = -1  // Assuming workoutId is passed or retrieved

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve workoutId from arguments or saved state
        workoutId = arguments?.getInt("workoutId") ?: -1

        // Initialize RecyclerView and Adapter with an empty list
        workoutLogAdapter = WorkoutLogAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_workout_log)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = workoutLogAdapter

        // Initialize database and repository
        val database = AppDatabase.getDatabase(requireContext().applicationContext)
        val workoutDao = database.workoutDao()
        val workoutRepository = WorkoutRepository(workoutDao)

        // Initialize the ViewModel with the factory
        val factory = WorkoutLogViewModelFactory(workoutRepository)
        workoutLogViewModel = ViewModelProvider(this, factory).get(WorkoutLogViewModel::class.java)

        // Fetch the workout exercises and update the adapter
        if (workoutId != -1) {
            workoutLogViewModel.getWorkoutExercises(workoutId).observe(viewLifecycleOwner, { exercises: List<ExerciseWithSets> ->
                workoutLogAdapter.setExercises(exercises)
            })
        }

        // Submit button functionality
        view.findViewById<Button>(R.id.btn_submit_workout).setOnClickListener {
            workoutLogViewModel.submitWorkout(workoutLogAdapter.getUpdatedData())
        }
    }
}
