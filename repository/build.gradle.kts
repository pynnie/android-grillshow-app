import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = false) {
    namespace = "de.shecken.grillshow.repository"
}

dependencies {
    baseDependencies()
    baseTestDependencies()

    implementation(project(":networking"))
    implementation(project(":database"))
    implementation(project(":shared"))
    implementation(AndroidX.dataStore.preferences)
}
