// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
       /* classpath (libs.hilt.classpath)*/
        classpath(libs.objectbox.classpath)

    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id ("com.google.dagger.hilt.android") version "2.48.1" apply false // Adjust version if needed
}