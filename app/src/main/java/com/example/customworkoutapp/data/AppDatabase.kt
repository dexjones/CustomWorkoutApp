package com.example.customworkoutapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.customworkoutapp.data.entities.*
import com.example.customworkoutapp.data.dao.*

@Database(
    entities = [
        User::class,
        Workout::class,
        WorkoutPlan::class,
        Exercise::class,
        ProgressLog::class
    ],
    version = 1, // Increment this version whenever you make schema changes
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutPlanDao(): WorkoutPlanDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun progressLogDao(): ProgressLogDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workout_db"
                ).build().also { instance = it }
            }
    }
}