import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            val extension = extensions.getByType<LibraryExtension>()
            extension.apply {
                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
                buildFeatures.apply {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("compose").get().toString()
                }

                dependencies {
                    add("implementation", libs.findLibrary("compose-activity").get())
                    add("implementation", libs.findLibrary("compose-ui").get())
                    add("implementation", libs.findLibrary("compose-tooling-preview").get())
                    add("implementation", libs.findLibrary("compose-tooling").get())
                    add("implementation", libs.findLibrary("compose-material3").get())
                    add("implementation", libs.findLibrary("compose-icons-extended").get())
                    add("implementation", libs.findLibrary("compose-navigation").get())
                }
            }
        }
    }
}
