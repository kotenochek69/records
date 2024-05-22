plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"


java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-devtools") //todo
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    implementation("org.liquibase:liquibase-core:4.27.0")

    testImplementation("org.testcontainers:postgresql:1.19.8")
    testImplementation("org.testcontainers:junit-jupiter:1.19.8")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")


}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test{
}

tasks.asciidoctor {
}
