package com.example.customworkoutapp.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.R
import com.example.customworkoutapp.ui.HomeActivity
import com.example.customworkoutapp.viewmodel.GoalViewModel
import com.example.customworkoutapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrequencyFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_frequency, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        email = requireActivity().intent.getStringExtra("email") ?: ""

        val frequencyRadioGroup = view.findViewById<RadioGroup>(R.id.frequency_radio_group)
        val finishButton = view.findViewById<Button>(R.id.btn_finish_frequency)

        finishButton.setOnClickListener {
            val selectedFrequencyText = view.findViewById<RadioButton>(
                frequencyRadioGroup.checkedRadioButtonId
            ).text.toString()

            // Strip non-numeric characters
            val numericPart = selectedFrequencyText.replace(Regex("[^\\d]"), "")

            // Convert the numeric part to an integer, default to 0 if it fails
            val selectedFrequency = numericPart.toIntOrNull() ?: 0

            if (selectedFrequency > 0) {
                goalViewModel.updateFrequency(selectedFrequency)

                // Save the goal and workout plan, then navigate to HomeActivity
                saveGoalAndWorkoutPlan()
            }
        }
        return view
    }

    private fun saveGoalAndWorkoutPlan() {
        // Fetch userId dynamically from SharedPreferences and convert back to Long
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userIdString = sharedPreferences.getString("userId", null)

        // Ensure userId is not null and convert it to Long
        if (userIdString != null) {
            val userId = userIdString.toLongOrNull()

            // Proceed only if userId conversion is successful
            if (userId != null && userId > 0) {
                // Create the goal from GoalViewModel
                val goal = goalViewModel.createGoal()

                // Save the goal and workout plan using UserViewModel
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        // Insert the goal into the database and get the auto-generated goalId
                        val goalId = userViewModel.insertGoal(goal)

                        // Ensure that the goal was inserted successfully by checking if goalId is greater than 0
                        if (goalId > 0L) {
                            // Generate workout plans associated with the goal and user
                            val workoutPlans = goalViewModel.generateWorkoutPlans(userId.toInt(), goalId.toInt())

                            // Log the userId and goalId for debugging
                            Log.d("WorkoutPlanInsert", "Inserting WorkoutPlan with userId: $userId, goalId: $goalId")

                            // Insert the workout plans after the goal is successfully inserted
                            userViewModel.insertWorkoutPlans(workoutPlans)

                            // Navigate to HomeActivity after saving
                            navigateToHome()
                        }
                    } catch (e: Exception) {
                        Log.e("SaveGoalError", "Error saving goal and workout plan", e)
                    }
                }
            } else {
                Log.e("FrequencyFragment", "Invalid User ID. Cannot save goal and workout plan.")
            }
        } else {
            Log.e("FrequencyFragment", "User ID not found. Cannot save goal and workout plan.")
        }
    }

    private fun navigateToHome() {
        CoroutineScope(Dispatchers.Main).launch {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
