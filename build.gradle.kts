plugins {
    java
    kotlin("jvm") version "1.5.0"

    id("info.solidsoft.pitest") version "1.6.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:3.10.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

pitest {
    //adds dependency to org.pitest:pitest-junit5-plugin and sets "testPlugin" to "junit5"
    junit5PluginVersion.set("0.14")
    timestampedReports.set(false)

    targetClasses.set(listOf(
        "pl.refactoring.interpreter.legacy.*"
    ))
    targetTests.set(listOf(
        "pl.refactoring.interpreter.legacy.*"
    ))

    avoidCallsTo.set(listOf(
        "kotlin.jvm.internal"
    ))
}