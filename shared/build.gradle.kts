
import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies
import extensions.composeDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = true) {
    namespace = "de.shecken.grillshow.shared"
}

dependencies {
    baseDependencies()
    baseTestDependencies()
    composeDependencies()
    implementation(Koin.android)
}
