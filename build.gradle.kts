import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    war
    id("org.jetbrains.kotlin.jvm") version "1.3.11" apply false
    id("org.springframework.boot") version "2.1.2.RELEASE" apply false
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.11" apply false
}

group = "fr.isima"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
}

subprojects {

    buildscript {
        repositories {
            gradlePluginPortal()
        }

        dependencies {
            classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.2.RELEASE")
        }
    }
    apply(plugin = "war")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    
    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        compile("com.fasterxml.jackson.core:jackson-databind:2.9.8")
        compile("org.apache.commons:commons-lang3:3.8.1")
        compile("javax.inject:javax.inject:1")
        //compile ("postgresql:postgresql:42.2.5")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-tomcat")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
        implementation("org.springframework.boot:spring-boot-devtools")
        implementation("com.h2database:h2")
        implementation("javax.servlet:jstl")
        implementation("org.apache.tomcat.embed:tomcat-embed-jasper")

        testCompile("org.assertj:assertj-core:3.8.0")
        testCompile("org.mockito:mockito-core:2.13.0")
        testCompile("junit:junit:4.12")
    }

    //reload resources dinamycally
    tasks.getByName<BootRun>("bootRun") {

        sourceResources(sourceSets["main"])
    }

    val javaVersion = JavaVersion.VERSION_1_8.toString()
    tasks.getByName<JavaCompile>("compileJava") {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    tasks.getByName<JavaCompile>("compileTestJava") {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}