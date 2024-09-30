package com.example.customworkoutapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.entities.User
import com.example.customworkoutapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileSetupActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var email: String  // Hold the email passed from SignUpActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_setup)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Get the email passed from SignUpActivity
        email = intent.getStringExtra("email") ?: ""

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            // Collect the additional profile details from the EditText fields
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val age = findViewById<EditText>(R.id.et_age).text.toString().toIntOrNull() ?: 0
            val weight = findViewById<EditText>(R.id.et_weight).text.toString().toFloatOrNull() ?: 0f
            val feet = findViewById<EditText>(R.id.et_feet).text.toString().toIntOrNull() ?: 0
            val inches = findViewById<EditText>(R.id.et_inches).text.toString().toIntOrNull() ?: 0
            val totalHeightInInches = (feet * 12) + inches
            val goal = findViewById<EditText>(R.id.et_goal).text.toString()

            // Fetch the existing user object from the database and update it
            CoroutineScope(Dispatchers.IO).launch {
                val existingUser = viewModel.getUserByEmail(email)

                if (existingUser != null) {
                    // Update user profile with additional information
                    val updatedUser = existingUser.copy(
                        name = name,
                        age = age,
                        weight = weight,
                        height = totalHeightInInches.toFloat(),
                        goal = goal
                    )

                    // Insert or update the user profile in the database
                    viewModel.insertUser(updatedUser)

                    // Switch to main thread to navigate to HomeActivity
                    launch(Dispatchers.Main) {
                        startActivity(Intent(this@UserProfileSetupActivity, HomeActivity::class.java))
                        finish() // Finish UserProfileSetupActivity so it can't be returned to
                    }
                }
            }
        }
    }
}
