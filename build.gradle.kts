import extensions.commitCount

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:_")
        classpath("com.mikepenz.aboutlibraries.plugin:aboutlibraries-plugin:_")
    }
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        google()
        mavenCentral()
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
