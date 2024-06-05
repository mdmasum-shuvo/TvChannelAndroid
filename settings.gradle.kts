pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")

        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")

    }
}

rootProject.name = "Fly Tv"
include(":app")
include(":network")
include(":cachemanager")
include(":app_data_source")
