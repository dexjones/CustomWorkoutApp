package com.example.customworkoutapp.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.customworkoutapp.R
import com.example.customworkoutapp.ui.fragments.ProfileFragment
import com.example.customworkoutapp.ui.fragments.ProgressTrackingFragment
import com.example.customworkoutapp.ui.fragments.WorkoutLogFragment
import com.example.customworkoutapp.ui.fragments.WorkoutPlanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        welcomeTextView = findViewById(R.id.home_welcome_text)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Handle navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_workout_plan -> {
                    loadFragment(WorkoutPlanFragment())
                    hideWelcomeMessage()
                    true
                }
                R.id.navigation_log -> {
                    loadFragment(WorkoutLogFragment())
                    true
                }
                R.id.navigation_progress -> {
                    loadFragment(ProgressTrackingFragment())
                    true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)  // This must match the fragment container ID in your layout
            .commit()
    }

    private fun hideWelcomeMessage() {
        welcomeTextView.visibility = View.GONE
    }
}
