plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.momid"
version = "0.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":TraDeep"))
    implementation(project(":ParsingRules"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}
