
import extensions.androidAppConfig
import extensions.baseDependencies
import extensions.baseFeatureModuleDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
}

androidAppConfig()

dependencies {
    baseDependencies()
    baseTestDependencies()
    implementation(project(":features:shop"))
    implementation(project(":features:dashboard"))

    implementation(project(":repository"))
    implementation(project(":networking"))
    implementation(project(":database"))
    implementation(AndroidX.appCompat)
    implementation(Google.Android.material)
    implementation(AndroidX.Core.splashscreen)

    baseFeatureModuleDependencies(withCompose = true)

    implementation(KotlinX.serialization.core)
}
