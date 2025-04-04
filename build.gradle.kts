import org.gradle.kotlin.dsl.support.isGradleKotlinDslJar

val restdocsApiSpecVersion: String by extra { "0.19.4" }

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("kapt") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    kotlin("plugin.jpa") version "2.1.0"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.epages.restdocs-api-spec") version "0.19.4"
}

fun getGitHash(): String {
    return providers.exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
    }.standardOutput.asText.get().trim()
}

group = "kr.hhplus.be"
version = getGitHash()

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmToolchain(17)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.0")
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // DB
    runtimeOnly("com.mysql:mysql-connector-j")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // REST Docs
    testImplementation("com.epages:restdocs-api-spec-mockmvc:${restdocsApiSpecVersion}")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("user.timezone", "UTC")
}

openapi3 {
    setServer("https://localhost:8080")
    title = "E-Commerce API"
    description = """
                  | E-Commerce API 명세서입니다.
                  """.trimMargin()
    version = "1.0.0"
    format = "yaml"
}

tasks.register<Copy>("copyOpenApiSpec") {
    delete("${layout.projectDirectory}/src/main/resources/static/swagger-ui/openapi3.yaml")
    from("${layout.buildDirectory}/api-spec/openapi3.yaml")
    into("${layout.projectDirectory}/src/main/resources/static/swagger-ui/.")
    onlyIf { file("${layout.buildDirectory}/api-spec/openapi3.yaml").exists() }
}

tasks.named("build") {
    finalizedBy("copyOpenApiSpec")
}
