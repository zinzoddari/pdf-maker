import com.github.jk1.license.filter.ExcludeTransitiveDependenciesFilter
import com.github.jk1.license.filter.LicenseBundleNormalizer
import com.github.jk1.license.render.JsonReportRenderer

plugins {
    id("java")
    id("maven-publish")
    id("com.github.jk1.dependency-license-report") version "2.9"
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

    implementation("com.itextpdf:itextpdf:5.5.13.4")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = group.toString()
            artifactId = "pdf-maker"
            version = version.toString()

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}

licenseReport {
    unionParentPomLicenses = false
    allowedLicensesFile = File("$projectDir/config/allowed-licenses.json")
    renderers = arrayOf(JsonReportRenderer("check-license-details.json"))
}
