import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    signing
    java
    `java-library`
    `maven-publish`
}

val prefixedName = "core-${project.name}"

tasks {
    processResources {
        filter<ReplaceTokens>("tokens" to mapOf(
            "name" to rootProject.name,
            "version" to project.version
        ))
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    jar {
        archiveBaseName.set(prefixedName)
    }
    publishing {
        repositories {
            maven {
                name = "novaReleases"
                url = uri("https://repo.novaserver.xyz/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
            maven {
                name = "novaSnapshots"
                url = uri("https://repo.novaserver.xyz/snapshots")
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                artifactId = prefixedName
                from(components["java"])
            }
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}