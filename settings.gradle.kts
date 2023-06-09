rootProject.name = "Grillshow"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.51.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

include(":app")
include(":features:dashboard")
include(":features:favorites")
include(":networking")
include(":repository")
include(":database")
include(":shared")
include(":sharedTest")
include(":features:info")
