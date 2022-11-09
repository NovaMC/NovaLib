plugins {
    id("core.parent")
}

val platforms = setOf(
    projects.paper,
    projects.velocity
).map { it.dependencyProject }

subprojects {
    when (this) {
        in platforms -> plugins.apply("core.platform-conventions")
        else -> plugins.apply("core.base-conventions")
    }
}