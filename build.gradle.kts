plugins {
    val springBootVersion = "3.3.1"
    val ktlintVersion = "12.1.1"
    val springDependencyManagementVersion = "1.1.5"
    val kotlinPluginsVersion = "2.0.0"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
    kotlin("plugin.jpa") version kotlinPluginsVersion
    kotlin("jvm") version kotlinPluginsVersion
    kotlin("plugin.spring") version kotlinPluginsVersion
}

group = "project.personal"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val kotlinLoggingJvm = "6.0.9"
val springdocOpenapiStarterWebMvcUiVersion = "2.5.0"
val kotestAssertionsCoreJvm = "5.9.1"
val mockkVersion = "1.13.11"

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingJvm")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiStarterWebMvcUiVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestAssertionsCoreJvm")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotation("jakarta.persistence.Entity")
}

configurations {
    all {
        exclude("commons-logging", "commons-logging")
    }
}
