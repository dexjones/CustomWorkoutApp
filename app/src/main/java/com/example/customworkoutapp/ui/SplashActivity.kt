package com.example.customworkoutapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.customworkoutapp.R
import com.example.customworkoutapp.utils.SessionManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Check if the user is already logged in
        Handler(Looper.getMainLooper()).postDelayed({
            val sessionManager = SessionManager(this)

            if (sessionManager.isLoggedIn()) {
                // User is already logged in, redirect to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // User is not logged in, show the LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
