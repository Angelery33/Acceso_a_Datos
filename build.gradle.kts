plugins {
    id("java")
}

group = "com.angelcantero"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20230618")
    implementation("org.json:json:20250517")
    implementation("org.postgresql:postgresql:42.7.8")

}

tasks.test {
    useJUnitPlatform()
}