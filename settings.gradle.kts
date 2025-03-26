pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "EmotionalCompanionship"
include(":app")
 