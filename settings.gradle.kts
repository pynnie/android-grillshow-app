rootProject.name = "Grillshow"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.1"
}

refreshVersions {
    enableBuildSrcLibs()
}

include(":app")
include(":features:shop")
include(":features:video")
include(":networking")
include(":repository")
include(":database")
include(":shared")
include(":sharedTest")
