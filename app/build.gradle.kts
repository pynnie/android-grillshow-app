import extensions.androidAppConfig
import extensions.baseDependencies
import extensions.baseFeatureModuleDependencies
import extensions.baseTestDependencies
import extensions.composeDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.mikepenz.aboutlibraries.plugin")
    id("com.google.devtools.ksp")
}

androidAppConfig()

dependencies {
    baseDependencies()
    baseTestDependencies()
    composeDependencies()
    implementation(project(":features:dashboard"))
    implementation(project(":features:favorites"))
    implementation(project(":features:info"))

    implementation(project(":repository"))
    implementation(project(":networking"))
    implementation(project(":database"))
    implementation(AndroidX.appCompat)
    implementation(AndroidX.Core.splashscreen)
    implementation(Google.Android.material)

    baseFeatureModuleDependencies(withCompose = true)

    implementation(KotlinX.serialization.core)
}
