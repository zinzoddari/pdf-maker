plugins {
    id("java")
    id("maven-publish")
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

    // template engine
    implementation("org.freemarker:freemarker:2.3.34")

    // Encryption, 템플릿 생성시, 암호화 처리에 필요.
    implementation("org.bouncycastle:bcprov-jdk18on:1.79")
}

tasks.test {
    useJUnitPlatform()
}

