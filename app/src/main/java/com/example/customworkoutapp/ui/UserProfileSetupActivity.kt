package com.example.customworkoutapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.repository.ExerciseRepository
import com.example.customworkoutapp.data.repository.GoalRepository
import com.example.customworkoutapp.ui.fragments.IntentFragment
import com.example.customworkoutapp.viewmodel.UserViewModel
import com.example.customworkoutapp.viewmodel.GoalViewModel
import com.example.customworkoutapp.viewmodel.GoalViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileSetupActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var goalViewModel: GoalViewModel
    private lateinit var email: String  // Hold the email passed from SignUpActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_setup)

        // Initialize the database and repository
        val goalDao = AppDatabase.getDatabase(application).goalDao()
        val goalRepository = GoalRepository(goalDao)

        val exerciseDao = AppDatabase.getDatabase(application).exerciseDao()
        val exerciseRepository = ExerciseRepository(exerciseDao)

        // Initialize the GoalViewModel using the custom factory
        val goalViewModelFactory = GoalViewModelFactory(goalRepository, exerciseRepository)
        goalViewModel = ViewModelProvider(this, goalViewModelFactory).get(GoalViewModel::class.java)

        // Initialize UserViewModel as usual
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Get the email passed from SignUpActivity
        email = intent.getStringExtra("email") ?: ""

        // Your existing code for button click handling
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            // Collect and validate basic profile details
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val age = findViewById<EditText>(R.id.et_age).text.toString().toIntOrNull() ?: 0
            val weight = findViewById<EditText>(R.id.et_weight).text.toString().toFloatOrNull() ?: 0f
            val feet = findViewById<EditText>(R.id.et_feet).text.toString().toIntOrNull() ?: 0
            val inches = findViewById<EditText>(R.id.et_inches).text.toString().toIntOrNull() ?: 0
            val totalHeightInInches = (feet * 12) + inches

            if (name.isBlank() || age <= 0 || weight <= 0 || feet <= 0 || inches < 0 || inches >= 12) {
                return@setOnClickListener
            }

            // Hide input fields and show fragment
            findViewById<EditText>(R.id.et_name).visibility = View.GONE
            findViewById<EditText>(R.id.et_age).visibility = View.GONE
            findViewById<EditText>(R.id.et_weight).visibility = View.GONE
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.height_input_container).visibility = View.GONE
            findViewById<Button>(R.id.btn_save).visibility = View.GONE
            findViewById<androidx.fragment.app.FragmentContainerView>(R.id.fragment_container).visibility = View.VISIBLE

            // Save basic user info without goalId
            CoroutineScope(Dispatchers.IO).launch {
                val existingUser = userViewModel.getUserByEmail(email)

                if (existingUser != null) {
                    val updatedUser = existingUser.copy(
                        name = name,
                        age = age,
                        weight = weight,
                        height = totalHeightInInches.toFloat(),
                        goalId = null,  // Goal will be set after goal fragments
                        isProfileComplete = false
                    )
                    userViewModel.insertUser(updatedUser)
                }

                // Switch to goal setup (IntentFragment)
                launch(Dispatchers.Main) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, IntentFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}

