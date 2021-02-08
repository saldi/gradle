plugins {
    id("gradlebuild.distribution.api-java")
}

dependencies {
    api(project(":resources"))
    implementation(project(":base-services"))
    implementation(project(":core-api"))
    implementation(project(":core"))
    implementation(project(":model-core"))
    implementation(project(":logging"))

    implementation(libs.commonsHttpclient)
    implementation(libs.slf4jApi)
    implementation(libs.jclToSlf4j)
    implementation(libs.jcifs)
    implementation(libs.guava)
    implementation(libs.commonsLang)
    implementation(libs.commonsIo)
    implementation(libs.xerces)
    implementation(libs.nekohtml)

    testImplementation(project(":internal-integ-testing"))
    testImplementation(libs.jettyWebApp)
    testImplementation(testFixtures(project(":core")))
    testImplementation(testFixtures(project(":logging")))

    testFixturesImplementation(project(":base-services"))
    testFixturesImplementation(project(":logging"))
    testFixturesImplementation(project(":internal-integ-testing"))
    testFixturesImplementation(libs.slf4jApi)

    integTestDistributionRuntimeOnly(project(":distributions-core"))
}
