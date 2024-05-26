plugins {
    id("java")
    id("idea")
    id("jacoco")
}

group = "de.unibw.gri"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter") {
        because("allows to write and run Jupiter tests")
    }
    testImplementation("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine") {
        because("allows JUnit 3 and JUnit 4 tests to run")
    }
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.test {
    useJUnitPlatform {
        // includeEngines("junit-jupiter", "junit-vintage")
        // excludeEngines("custom-engine")
        // includeTags("fast")
        excludeTags("slow")
    }

    testLogging {
        events("passed", "skipped", "failed")
    }

    finalizedBy(tasks.jacocoTestReport)
}