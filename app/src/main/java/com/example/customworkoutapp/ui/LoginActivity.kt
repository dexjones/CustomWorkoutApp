package com.example.customworkoutapp.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.customworkoutapp.R
import com.example.customworkoutapp.data.AppDatabase
import com.example.customworkoutapp.data.entities.User
import com.example.customworkoutapp.utils.SessionManager
import com.example.customworkoutapp.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var userViewModel: UserViewModel

    // Initialize database variable
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.et_email)
        passwordEditText = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)
        signUpButton = findViewById(R.id.btn_sign_up)

        // Initialize the Room database
        database = AppDatabase.getDatabase(this)

        // Initialize userViewModel
        userViewModel = UserViewModel(application)

        // Handle login button click
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateLoginInput(email, password)) {
                // Attempt login
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle sign-up button click
        signUpButton.setOnClickListener {
            // Navigate to the SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLoginInput(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = database.userDao().getUserByEmail(email)

            if (user != null && user.passwordHash == User.hashPassword(password, user.salt)) {
                // Login successful, navigate to the Home screen
                runOnUiThread {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else if (user == null) {
                // User not found, show dialog to sign up or retry
                runOnUiThread {
                    showEmailNotFoundDialog(email)
                }
            } else {
                // Invalid credentials
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Show dialog if email is not found
    private fun showEmailNotFoundDialog(email: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Account Not Found")
        builder.setMessage("There's no account associated with the email \"$email\". Would you like to sign up or try another email?")

        builder.setPositiveButton("Sign Up") { dialog, _ ->
            // Go to sign-up activity with the email pre-filled
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("email", email) // Pass the email to the sign-up activity
            startActivity(intent)
            dialog.dismiss()
        }

        builder.setNegativeButton("Try Again") { dialog, _ ->
            // Allow user to try logging in again with a different email
            emailEditText.text.clear()
            passwordEditText.text.clear()
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
