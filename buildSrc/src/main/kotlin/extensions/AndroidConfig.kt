package extensions

import AndroidX
import AppConfig
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import de.fayard.refreshVersions.core.versionFor
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.plugins

fun Project.androidAppConfig(additionalConfig: BaseAppModuleExtension.() -> Unit = { }) {
    apply {
        plugin("com.android.application")
        plugin("com.google.devtools.ksp")
    }
    extensions.configure<BaseAppModuleExtension> {
        compileSdk = AppConfig.compileSdk

        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            debug {
                isMinifyEnabled = false
            }
            release {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = versionFor(AndroidX.Compose.compiler)
        }

        additionalConfig()
    }
}

fun Project.androidLibraryConfig(
    withCompose: Boolean,
    additionalConfig: LibraryExtension.() -> Unit = { }
) {
    apply {
        plugin("com.android.library")
        plugin("com.google.devtools.ksp")
    }
    extensions.configure<LibraryExtension> {
        compileSdk = AppConfig.compileSdk

        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            buildFeatures {
                compose = withCompose
            }
            release {
                isMinifyEnabled = false
                consumerProguardFile("consumer-rules.pro")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        composeOptions {
            kotlinCompilerExtensionVersion = versionFor(AndroidX.Compose.compiler)
        }

        additionalConfig()
    }
}
