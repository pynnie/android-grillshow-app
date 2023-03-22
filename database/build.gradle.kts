import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

androidLibraryConfig(withCompose = false)

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
