plugins {
    id("java")
}

group = "com.maker.pdf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    //pdf
    implementation("org.xhtmlrenderer:flying-saucer-pdf:9.11.2")

    implementation("org.slf4j:slf4j-api:2.0.16")
}

tasks.test {
    useJUnitPlatform()
}