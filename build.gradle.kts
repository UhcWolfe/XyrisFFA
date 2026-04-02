plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "9.4.1"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

group = "dev.darkxx"
version = "1.0.5"
description = "FFA"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        url = uri("https://repo.dmulloy2.net/nexus/repository/public/")
    }
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
    maven {
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.apache.httpcomponents:httpmime:4.5.6")
    constraints {
        implementation("com.google.guava:guava:32.1.3-jre") {
            because("FAWE uses old version, WorldGuard uses strict version")
        }
        implementation("com.google.code.gson:gson:2.10.1") {
            because("FAWE vs WorldGuard conflict")
        }
    }
    // compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
    compileOnly("net.dmulloy2:ProtocolLib:5.4.0")
    compileOnly("me.clip:placeholderapi:2.11.5")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.12")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit:2.11.1") {
        exclude(group = "org.lz4", module = "lz4-java")
    }
    // compileOnly("com.github.Darkxx14:KitsX:master-SNAPSHOT")
}

configurations.all {
    resolutionStrategy {
        force("com.google.guava:guava:32.1.3-jre")
        force("com.google.code.gson:gson:2.10.1")
        force("it.unimi.dsi:fastutil:8.5.12")
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(tasks["shadowJar"])
    }
}

tasks.shadowJar {
    minimize()
    archiveFileName.set("${project.name}-${project.version}.jar")
    relocate("com.zaxxer:HikariCP", "dev.darkxx.ffa.shaded.com.zaxxer:HikariCP")
    exclude("dev/darkxx/xyriskits/api/**")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

project.buildDir = File(rootDir, "output")

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
