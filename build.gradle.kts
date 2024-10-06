// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    // Safe Args plugin for navigation
    id("androidx.navigation.safeargs.kotlin") version "2.6.0" apply false
}

buildscript {
    val kotlin_version by extra("1.9.0")
    val nav_version by extra("2.6.0")  // Navigation component version

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // Safe Args plugin to support navigation argument passing
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
