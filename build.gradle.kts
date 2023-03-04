plugins {
    kotlin("jvm") version "1.7.10"
}

group = "cz.hammermantuts"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Kotlin coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    // Other dependencies for your desktop app
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation ("org.slf4j:slf4j-simple:2.0.0-alpha1")
    // library for excel
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    // library for json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    // library for csv
    implementation("org.apache.commons:commons-csv:1.10.0")


}

tasks.test {
    useJUnitPlatform()
}