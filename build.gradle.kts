plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "3.5.8"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.graalvm.buildtools.native") version "0.10.6"
}

group = "com.jamesward"
version = "0.0.1-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(platform("org.springframework.ai:spring-ai-bom:1.1.1"))
    implementation("org.springframework.ai:spring-ai-starter-model-bedrock-converse")
    implementation("org.springframework.ai:spring-ai-starter-mcp-client")
}
