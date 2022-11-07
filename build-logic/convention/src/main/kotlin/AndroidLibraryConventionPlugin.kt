import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                val commonExtension = this as CommonExtension<*, *, *, *>

                commonExtension.apply {
                    compileSdk = 33

                    defaultConfig {
                        minSdk = 27
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_1_8
                        targetCompatibility = JavaVersion.VERSION_1_8
                    }

                    kotlinOptions {
                        // Treat all Kotlin warnings as errors (disabled by default)
                        // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
                        val warningsAsErrors: String? by project
                        allWarningsAsErrors = warningsAsErrors.toBoolean()

                        freeCompilerArgs = freeCompilerArgs + listOf(
                            "-opt-in=kotlin.RequiresOptIn",
                            // Enable experimental coroutines APIs, including Flow
                            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                            "-opt-in=kotlinx.coroutines.FlowPreview",
                            "-opt-in=kotlin.Experimental"
                        )

                        // Set JVM target to 1.8
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }

                defaultConfig.targetSdk = 33
            }
        }
    }
}
