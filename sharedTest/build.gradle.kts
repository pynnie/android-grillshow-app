import extensions.androidLibraryConfig
import extensions.baseDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = false) {
    namespace = "de.shecken.grillshow.sharedTest"
}

dependencies {
    baseDependencies()
    implementation(KotlinX.coroutines.test)
    implementation(Testing.kotest.assertions.core)
}
