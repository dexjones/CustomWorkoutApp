package com.example.customworkoutapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        const val USER_LOGGED_IN = "user_logged_in"
    }

    // Save the login status
    fun saveLoginStatus(isLoggedIn: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(USER_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    // Check if the user is logged in
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(USER_LOGGED_IN, false)
    }
    fun logout() {
        prefs.edit().clear().apply() // Clears the session, forcing a logout
    }

    // Clear session (for logging out)
    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}
