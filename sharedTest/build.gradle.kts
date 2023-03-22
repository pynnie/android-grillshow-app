import extensions.androidLibraryConfig
import extensions.baseDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = false)

dependencies {
    baseDependencies()
    implementation(KotlinX.coroutines.test)
    implementation(Testing.kotest.assertions.core)
}
