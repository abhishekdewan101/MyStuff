plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.common.ux"
}

dependencies {
    with(libs.compose) {
        implementation(navigation)
        implementation(icons.extended)
    }

    with(libs.accompanist) {
        implementation(system.ui.controller)
        implementation(flowlayout)
        implementation(pager)
    }
    implementation(libs.coil)
}
