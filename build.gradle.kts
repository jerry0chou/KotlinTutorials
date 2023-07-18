plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "org.jerry"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}

//kotlin { // do not use it on macos, it will raise errors
//    jvmToolchain(8)
//}

application {
    mainClass.set("MainKt")
}