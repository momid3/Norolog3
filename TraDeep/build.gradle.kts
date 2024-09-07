plugins {
    kotlin("jvm") version "2.0.0"
    id("com.google.devtools.ksp") version "2.0.0-1.0.23"
}

group = "com.momid"
version = "0.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.devtools.ksp:symbol-processing-api:2.0.0-1.0.21")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}
