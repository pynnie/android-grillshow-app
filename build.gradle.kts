import extensions.commitCount

buildscript {

    repositories {
        google()
        mavenCentral()
		gradlePluginPortal()
    }
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath(Kotlin.gradlePlugin)
        classpath(libs.ktlint.gradle)
        classpath(libs.aboutlibraries.plugin)
    }
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        google()
        mavenCentral()
		gradlePluginPortal()
    }
}

subprojects {
    tasks.withType<Delete>().all {
        delete(rootProject.buildDir)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.register("versionCode") {
    println(commitCount())
}

tasks.register("versionName") {
    println(AppConfig.versionName)
}
