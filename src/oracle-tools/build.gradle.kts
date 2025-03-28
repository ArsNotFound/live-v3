import org.gradle.kotlin.dsl.run as runTask

plugins {
    id("live.app-conventions")
}


base {
    archivesName = "oracle-tools"
}

application {
    mainClass = "org.icpclive.oracle.ApplicationKt"
}

tasks {
    runTask {
        this.args = listOfNotNull(
            project.properties["live.dev.configDirectory"]?.let { "--config-directory=$it" },
            project.properties["live.dev.overlayUrl"]?.let { "--overlay=$it" },
        )
        this.workingDir = rootDir.resolve(".")
    }

    // Not the best way of doing this, but should work out.
    processResources {
        into("locator") {
            from(project(":frontend").tasks.named("pnpm_run_buildLocatorAdmin"))
        }
    }
}

dependencies {
    implementation(libs.cli)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.core)
    implementation(projects.backendApi)
    implementation(projects.cds.full)
    implementation(projects.serverShared)
}
