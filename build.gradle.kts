plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "me.dio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Dependências essenciais
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.hibernate.validator:hibernate-validator")
	implementation("jakarta.annotation:jakarta.annotation-api")

	// Driver do PostgreSQL
	runtimeOnly("org.postgresql:postgresql")

	runtimeOnly ("com.h2database:h2")

	// Dependências de teste
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2") // Para uso durante testes apenas
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "me.dio.DecolaTech2025Application"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
