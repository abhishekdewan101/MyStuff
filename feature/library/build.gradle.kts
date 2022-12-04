plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.feature.library"
}

dependencies {
    implementation(project(mapOf("path" to ":common:ux")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:database"))) // FIXME: Workaround, we should have a common model class
    with(libs.compose) {
        implementation(navigation)
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }
    with(libs.accompanist) {
        implementation(pager)
        implementation(pager.indicators)
    }
    implementation(libs.coil)
}
