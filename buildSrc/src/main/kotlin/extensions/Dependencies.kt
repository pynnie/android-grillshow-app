package extensions

import AdditionalLibs
import AndroidX
import JakeWharton
import Koin
import Kotlin
import KotlinX
import Testing
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.baseDependencies() {
    add("implementation", Kotlin.stdlib)
    add("implementation", KotlinX.Coroutines.android)
    add("implementation", JakeWharton.timber)
    add("implementation", Koin.android)
}

fun DependencyHandlerScope.baseTestDependencies() {
    add("testImplementation", project(":sharedTest"))

    add("testImplementation", Testing.junit.jupiter.api)
    add("testRuntimeOnly", Testing.junit.jupiter.engine)
    add("testImplementation", Testing.mockK)
    add("testImplementation", Testing.kotest.assertions.core)
    add("testImplementation", KotlinX.coroutines.test)
    add("testImplementation", AdditionalLibs.turbine)
}

fun DependencyHandlerScope.baseFeatureModuleDependencies(withCompose: Boolean = true) {
    add("implementation", project(":repository"))
    add("implementation", project(":shared"))
    add("implementation", Koin.android)
    add("implementation", AndroidX.Core.ktx)
    if (withCompose) {
        composeDependencies()
    }
}

fun DependencyHandlerScope.composeDependencies() {
    add("implementation", AndroidX.Compose.ui)
    add("debugImplementation", AndroidX.Compose.Ui.tooling)
    add("implementation", AndroidX.Compose.Ui.toolingPreview)
    add("implementation", AndroidX.Compose.foundation)
    add("implementation", AndroidX.Compose.material3)
    add("implementation", AndroidX.Lifecycle.viewModelCompose)
    add("implementation", AndroidX.Lifecycle.runtime.compose)
    add("implementation", AndroidX.Activity.compose)
    add("implementation", AndroidX.ConstraintLayout.compose)
    add("implementation", AndroidX.Navigation.compose)
    add("implementation", AdditionalLibs.koinCompose)
    add("implementation", COIL.compose)
}
