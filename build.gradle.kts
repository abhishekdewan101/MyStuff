buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// The errors shown here are an AS bug https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.android.application) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.jvm) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.firebase) apply false
}
