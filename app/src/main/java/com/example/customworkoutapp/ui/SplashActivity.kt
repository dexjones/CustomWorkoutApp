package com.example.customworkoutapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Add a delay to simulate loading time (e.g., 3 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            val sessionManager = SessionManager(this)
            val db = AppDatabase.getDatabase(this)
            val userEmail = sessionManager.getUserEmail()

            if (sessionManager.isLoggedIn()) {
                CoroutineScope(Dispatchers.IO).launch {
                    // Get the logged-in user's profile
                    if (userEmail != null) {
                        val user = db.userDao().getUserByEmail(userEmail)

                        if (user != null) {
                            // Check if the user profile is complete
                            if (user.isProfileComplete) {
                                // If profile is complete, navigate to HomeActivity
                                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // If profile is incomplete, navigate to UserProfileSetupActivity
                                val intent = Intent(this@SplashActivity, UserProfileSetupActivity::class.java)
                                intent.putExtra("email", user.email)  // Pass email to setup activity
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            // If user not found, show login screen
                            navigateToLogin("User not found. Please log in.")
                        }
                    } else {
                        // If no user email found, show login screen
                        navigateToLogin("Please log in.")
                    }
                }
            } else {
                // If not logged in, navigate to LoginActivity
                navigateToLogin("Please log in.")
            }
        }, 3000)  // Delay of 3 seconds
    }

    private fun navigateToLogin(message: String) {
        runOnUiThread {
            Toast.makeText(this@SplashActivity, message, Toast.LENGTH_SHORT).show()
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
