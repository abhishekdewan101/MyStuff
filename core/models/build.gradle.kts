import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import com.google.protobuf.gradle.remove

plugins {
    id("java")
    id("kotlin")
    // The errors shown here are an AS bug https://youtrack.jetbrains.com/issue/KTIJ-19369
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.google.protobuf)
    kotlin("plugin.serialization") version "1.7.10"
}

sourceSets {
    main {
        java.srcDir("$projectDir/build/generated/source/proto/main/java")
        java.srcDir("$projectDir/build/generated/source/proto/main/kotlin")
    }
}

dependencies {
    // Both of these needs to be api to allow for the consuming modules to be able to access
    // supertypes
    with(libs.protobuf) {
        api(javalite)
        api(kotlin.lite)
    }

    with(libs.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    testImplementation(libs.junit4)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.5"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                remove("java")
            }
            task.plugins {
                create("java") {
                    option("lite")
                }
                create("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
