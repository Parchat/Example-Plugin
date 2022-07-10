plugins {
    // Java Plugin
    java

    // Shadow Jar Plugin for making fat jars using the shadowJar task
    id("com.github.johnrengelman.shadow") version "7.1.2"

    // Paper-weight
    id("io.papermc.paperweight.userdev") version "1.3.7"

    // Run paper server for easy testing.
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

// Set to Java 17 which Paper requires.
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

group = "me.example.exampleplugin"

// System.getEnv("BUILD_NUMBER") is if you use Jenkins to build otherwise it just appends SNAPSHOT.
version = "1.0.0-${System.getenv("BUILD_NUMBER") ?: "SNAPSHOT"}"
description = "Example Plugin!"

// A list of repositories
repositories {
    // A centralized hub for dependencies
    mavenCentral()

    // Paper API
    maven("https://repo.papermc.io/repository/maven-public/")
}

// Your Dependencies
dependencies {
    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.19-R0.1-SNAPSHOT")

    paperDevBundle("1.19-R0.1-SNAPSHOT")

    // An example of how to use implementation
    implementation("io.papermc:paperlib:1.0.7") {
        // Optional way to exclude certain modules.
        // This does not work for io.papermc because it doesn't exist in it.
        // exclude(group = "org.spigotmc", module = "spigot")
    }
}

// Plugin tasks so you can compile it
tasks {
    // Responsible for re-locating dependencies which reobfJar depends on.
    shadowJar {

        // Example of re-locating.
        listOf(
         "io.papermc"
        ).onEach { dep -> relocate(dep, "${rootProject.group}.plugin.lib.$dep") }

    }

    reobfJar {
        // Set the output name based on your project name & version.
        outputJar.set(rootProject.layout.buildDirectory.file("../run/plugins/${rootProject.name}-${rootProject.version}.jar"))
    }

    assemble {
        dependsOn(reobfJar)
    }

    // Set to Java 17 which Paper requires.
    compileJava {
        options.release.set(17)
    }

    // This is what processes your plugin.yml values.
    // It'll automatically replace these on compile.
    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to rootProject.group,
                "version" to rootProject.version,
                "description" to rootProject.description
            )
        }
    }
}