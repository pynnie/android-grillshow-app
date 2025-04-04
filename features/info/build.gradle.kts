import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseFeatureModuleDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = true) {
    namespace = "de.shecken.grillshow.info"
}

dependencies {
    baseDependencies()
    baseTestDependencies()
    baseFeatureModuleDependencies(withCompose = true)

    implementation(AdditionalLibs.aboutLibsCore)
    implementation(AdditionalLibs.aboutLibsCompose)
    implementation(libs.accompanist.webview)
}
