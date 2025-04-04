import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("jvm") version "_"
}

repositories {
    google()
    mavenCentral()
	gradlePluginPortal()
}

dependencies {
    implementation(Android.tools.build.gradlePlugin)
    implementation(kotlin("gradle-plugin", "_"))
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "17"
}