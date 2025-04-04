import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

androidLibraryConfig(withCompose = false) {
    namespace = "de.shecken.grillshow.database"
}

dependencies {
    baseDependencies()
    baseTestDependencies()

    implementation(AndroidX.security.cryptoKtx)
    implementation(AndroidX.room.runtime)
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)

    implementation(AdditionalLibs.sqlCipher)

    implementation(Koin.android)
}
