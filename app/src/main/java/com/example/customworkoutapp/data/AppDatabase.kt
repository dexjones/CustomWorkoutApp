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
        Exercise::class,
        ProgressLog::class,
        Equipment::class,
        ExerciseEquipment::class,
        ExerciseMuscleGroup::class,
        ExerciseProgressionVariation::class,
        ExerciseRegressionVariation::class,
        ExerciseTrainingMode::class,
        ExerciseType::class,
        Goal::class,
        MuscleGroup::class,
        ProgressionVariation::class,
        RegressionVariation::class,
        TrainingMode::class,
        Type::class,
        WorkoutPlan::class
    ],
    version = 1, // Increment this version whenever you make schema changes
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun progressLogDao(): ProgressLogDao
    abstract fun goalDao(): GoalDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun exerciseEquipmentDao(): ExerciseEquipmentDao
    abstract fun exerciseMuscleGroupDao(): ExerciseMuscleGroupDao
    abstract fun exerciseProgressionVariationDao(): ExerciseProgressionVariationDao
    abstract fun exerciseRegressionVariationDao(): ExerciseRegressionVariationDao
    abstract fun exerciseTrainingModeDao(): ExerciseTrainingModeDao
    abstract fun exerciseTypeDao(): ExerciseTypeDao
    abstract fun workoutPlanDao(): WorkoutPlanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Check if INSTANCE is null, if so create a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "exercise_database.db"
                )
                    .createFromAsset("exercise_database.db") // Load the pre-populated DB
                    .fallbackToDestructiveMigration() // DELETE AFTER DEVELOPMENT Destroys and rebuilds the database if the schema changes
                    .build()
                INSTANCE = instance // Set the instance
                instance // Return the new instance
            }
        }
    }
}