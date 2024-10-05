package com.example.customworkoutapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private var prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        const val USER_LOGGED_IN = "user_logged_in"
        private const val PREF_NAME = "UserSession"
        private const val KEY_EMAIL = "userEmail"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    // Save the login status
    fun saveLoginStatus(isLoggedIn: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(USER_LOGGED_IN, isLoggedIn)
        editor.apply()
    }
    fun saveUserEmail(email: String) {
        editor.putString(KEY_EMAIL, email)
        editor.putBoolean(KEY_IS_LOGGED_IN, true) // Mark user as logged in
        editor.apply()
    }
    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    // Check if the user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    fun logout() {
        editor.clear()
        editor.apply()
    }
}
