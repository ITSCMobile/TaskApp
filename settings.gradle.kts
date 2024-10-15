pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    // See https://splitties.github.io/refreshVersions
    // ./gradlew refreshVersions
    id("de.fayard.refreshVersions") version "0.60.5"
}

rootProject.name = "TasksMaster"
include(":app")
 