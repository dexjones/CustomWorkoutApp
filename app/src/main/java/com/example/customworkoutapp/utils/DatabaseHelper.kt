package com.example.customworkoutapp.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class DatabaseHelper(private val context: Context) {

    private val DB_NAME = "exercise_database.db"  // The name of your .db file in assets
    private val DB_PATH = context.getDatabasePath(DB_NAME).absolutePath  // Proper database path

    private val dbPath: String = context.applicationInfo.dataDir + "/databases/"

    // Check if the database already exists in the internal storage
    private fun checkDatabase(): Boolean {
        val dbFile = File(DB_PATH)
        return dbFile.exists()
    }

    // Get the path to the database
    fun getDatabasePath(): String {
        return dbPath + DB_NAME
    }
    // Copy the database from the assets folder to the internal storage
    private fun copyDatabase() {
        val input: InputStream = context.assets.open(DB_NAME) // Open database from assets
        val output: OutputStream = FileOutputStream(DB_PATH) // Output to internal storage

        val buffer = ByteArray(1024)
        var length: Int
        while (input.read(buffer).also { length = it } > 0) {
            output.write(buffer, 0, length)
        }

        // Close streams
        output.flush()
        output.close()
        input.close()
    }

    // Open the database or copy it from assets if it doesn't exist
    fun openDatabase() {
        if (!checkDatabase()) {
            copyDatabase() // Copy the database only if it doesn't exist in internal storage
        }
    }
}
