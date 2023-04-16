import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseFeatureModuleDependencies
import extensions.baseTestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibraryConfig(withCompose = true) {
    defaultConfig {
        buildConfigField("String", "FACEBOOK_URL", "\"https://www.facebook.com/Grillshow/\"")
        buildConfigField("String", "TIKTOK_URL", "\"https://www.tiktok.com/@diegrillshow\"")
        buildConfigField(
            "String",
            "INSTAGRAM_URL",
            "\"https://instagram.com/diegrillshow?igshid=YmMyMTA2M2Y=\""
        )
        buildConfigField("String", "YOUTUBE_URL", "\"https://www.youtube.com/@Grillshow\"")
    }
}

dependencies {
    baseDependencies()
    baseTestDependencies()
    baseFeatureModuleDependencies(withCompose = true)
}
