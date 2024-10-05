package com.example.customworkoutapp.ui

import com.example.customworkoutapp.utils.DatabaseHelper
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.customworkoutapp.R
import com.example.customworkoutapp.ui.fragments.ProfileFragment
import com.example.customworkoutapp.ui.fragments.ProgressTrackingFragment
import com.example.customworkoutapp.ui.fragments.WorkoutLogFragment
import com.example.customworkoutapp.ui.fragments.WorkoutPlanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val dbHelper = DatabaseHelper(this)
        dbHelper.openDatabase()

        val db = SQLiteDatabase.openDatabase(dbHelper.getDatabasePath(), null, SQLiteDatabase.OPEN_READWRITE)
        // Handle navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_workout_plan -> {
                    loadFragment(WorkoutPlanFragment())
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
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

