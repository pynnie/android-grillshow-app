rootProject.name = "Grillshow"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.1"
////                            # available:"0.40.2"
////                            # available:"0.50.0"
////                            # available:"0.50.1"
////                            # available:"0.50.2"
////                            # available:"0.51.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

include(":app")
include(":features:shop")
include(":features:dashboard")
include(":networking")
include(":repository")
include(":database")
include(":shared")
include(":sharedTest")
