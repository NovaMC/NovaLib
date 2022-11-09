plugins {
    id("core.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    shadowJar {
        archiveFileName.set("${rootProject.name}-${project.name}-${project.version}.jar")

        // Exclude unneeded Reflections libraries
        exclude("com/google/**")
        exclude("javax/**")
        exclude("org/slf4j/**")
        exclude("javassist/util/**")
        exclude("javassist/tools/**")
        exclude("javassist/scopedpool/**")
        exclude("javassist/runtime/**")
        exclude("javassist/expr/**")
        exclude("javassist/convert/**")
        exclude("javassist/compiler/**")

        relocate("javassist", "xyz.novaserver.core.libs.javassist")
        relocate("org.reflections", "xyz.novaserver.core.libs.org.reflections")
    }
    build {
        dependsOn(shadowJar)
    }
}
