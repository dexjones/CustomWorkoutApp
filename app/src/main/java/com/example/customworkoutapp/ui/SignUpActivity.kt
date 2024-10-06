package com.example.customworkoutapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var createAccountButton: Button
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEditText = findViewById(R.id.et_email_sign_up)
        passwordEditText = findViewById(R.id.et_password_sign_up)
        confirmPasswordEditText = findViewById(R.id.et_confirm_password_sign_up)
        createAccountButton = findViewById(R.id.btn_create_account)

        val preFilledEmail = intent.getStringExtra("email")
        if (!preFilledEmail.isNullOrEmpty()) {
            emailEditText.setText(preFilledEmail)
        }
        // Initialize Room Database
        database = AppDatabase.getDatabase(this)

        // Handle the sign-up button click
        createAccountButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (validateSignUpInput(email, password, confirmPassword)) {
                signUpUser(email, password)
            } else {
                Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateSignUpInput(email: String, password: String, confirmPassword: String): Boolean {
        return email.isNotEmpty() && password == confirmPassword && password.length >= 6
    }

    private fun signUpUser(email: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val existingUserCount = database.userDao().checkUserExists(email)

            if (existingUserCount > 0) {
                runOnUiThread {
                    Toast.makeText(this@SignUpActivity, "User already exists. Please log in.", Toast.LENGTH_SHORT).show()
                }
            } else {
                val salt = User.generateSalt()  // Generate a random salt
                val hashedPassword = User.hashPassword(password, salt)
                val user = User(email = email, passwordHash = hashedPassword, salt = salt, name = "")

                val userId = database.userDao().insertUser(user)

                // Store the userId in SharedPreferences for later use
                val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userId", userId.toString()) // Store userId as a string
                editor.putString("userEmail", user.email) // Store the email in SharedPreferences

                editor.apply()

                runOnUiThread {
                    val intent = Intent(this@SignUpActivity, UserProfileSetupActivity::class.java)
                    intent.putExtra("email", user.email)
                    startActivity(intent)
                    Toast.makeText(this@SignUpActivity, "Account created! Please log in.", Toast.LENGTH_SHORT).show()
                    finish() // Go back to login screen
                }
            }
        }
    }

}
