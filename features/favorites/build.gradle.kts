import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseFeatureModuleDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = true) {
    namespace = "de.shecken.grillshow.favorites"
}

dependencies {
    baseDependencies()
    baseFeatureModuleDependencies(withCompose = true)
    baseTestDependencies()
}