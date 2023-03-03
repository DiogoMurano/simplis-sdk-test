buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("io.ktor.plugin:plugin:2.2.4")
    }
}

plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "is.simpl"
version = "1.0.0"

repositories {
    mavenCentral()
}

val ktorVersion = "2.2.4"

dependencies {

    // -- Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-core:2.14.2")

    // -- Ktor
    implementation("io.ktor:ktor-client-core-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-client-logging-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-client-content-negotiation-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-jackson-jvm:${ktorVersion}")
}
