import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = false)

dependencies {
    baseDependencies()
    baseTestDependencies()

    implementation(project(":networking"))
    implementation(project(":database"))

    implementation(Koin.android)
}
