package configurations

import common.Os.LINUX
import jetbrains.buildServer.configs.kotlin.v2019_2.AbsoluteId
import model.CIBuildModel
import model.Stage

class SanityCheck(model: CIBuildModel, stage: Stage) : BaseGradleBuildType(model, stage = stage, usesParentBuildCache = true, init = {
    id = AbsoluteId(buildTypeId(model))
    name = "Sanity Check"
    description = "Static code analysis, checkstyle, release notes verification, etc."

    params {
        param("env.JAVA_HOME", LINUX.javaHomeForGradle())
    }

    features {
        publishBuildStatusToGithub(model)
        triggeredOnPullRequests()
    }

    applyDefaults(
            model,
            this,
            "sanityCheck",
            extraParameters = "-DenableCodeQuality=true ${buildScanTag("SanityCheck")} " + "-Porg.gradle.java.installations.auto-download=false"
    )
}) {
    companion object {
        fun buildTypeId(model: CIBuildModel) = "${model.projectId}_SanityCheck"
    }
}
