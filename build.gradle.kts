buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// The errors shown here are an AS bug https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}
