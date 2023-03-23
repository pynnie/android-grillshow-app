@file:Suppress("UnstableApiUsage")

import extensions.androidLibraryConfig
import extensions.baseDependencies
import extensions.baseTestDependencies
import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("plugin.serialization")
}

androidLibraryConfig(withCompose = false) {
    defaultConfig {
        val props = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
        val key = props.getProperty("youtube-data-api-key")
        buildConfigField("String", "YOUTUBE_DATA_API_KEY", key)
        buildConfigField("String", "YOUTUBE_DATA_API_BASE_URL", "\"https://youtube.googleapis.com/youtube/v3/\"")
        buildConfigField("String", "GRILLSHOW_UPLOADS_PLAYLIST_ID", "\"UUHqicoqlisG422NE9ZXIfJw\"")
        buildConfigField("String", "GRILLSHOW_CHANNEL_ID", "\"UCHqicoqlisG422NE9ZXIfJw\"")
    }
}

dependencies {
    baseDependencies()
    baseTestDependencies()

    implementation(Koin.android)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2.retrofit)
    implementation(JakeWharton.retrofit2.converter.kotlinxSerialization)
    implementation(KotlinX.serialization.core)
    implementation(KotlinX.serialization.json)
    debugImplementation(Chucker.library)
    releaseImplementation(Chucker.libraryNoOp)
}
