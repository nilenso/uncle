val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val daggerVersion: String by project

plugins {
    application
    kotlin("jvm") version "2.0.10"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.10"
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
}

group = "com.nilenso.uncle"
version = "0.0.1"

application {
    mainClass.set("com.nilenso.uncle.webserver.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host-jvm")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("com.google.dagger:dagger:$daggerVersion")
    ksp("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")
    implementation("com.zaxxer:HikariCP:5.1.0")

    implementation("org.flywaydb:flyway-core:10.17.2")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.17.2")

    implementation("org.apache.logging.log4j:log4j-api:2.23.1")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    implementation("org.apache.logging.log4j:log4j-layout-template-json:2.23.1")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.7.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.11.0")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine:1.11.0")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation(kotlin("test"))
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")
    testImplementation("org.testcontainers:junit-jupiter:1.20.1")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}