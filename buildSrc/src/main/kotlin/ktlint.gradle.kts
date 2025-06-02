// ktlint.gradle.kts
// Created by Ulises Gonzalez
// Copyright (c) 2025. All rights reserved
@file:Suppress("DEPRECATION")

val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.49.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

val ktlinCheck by tasks.registering(JavaExec::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args(
        listOf(
            "--disabled_rules=no-wildcard-imports," + // Prohibits imports with *
                    "argument-list-wrapping," + // Wraps argument lists
                    "spacing-around-angle-brackets," + // Requires spacing around angle brackets
                    "class-naming," + // Requires class naming
                    "function-signature," + // Requires function signature
                    "block-comment-initial-star-alignment," + // Requires block comment initial star alignment
                    "chain-wrapping," + // Wraps chains
                    "function-return-type-spacing", // Requires spacing around function return type
            "**/src/**/*.kt",
            "**.kts",
            "!**/build/**",
            "--reporter=plain",
            "--color",
            "--reporter=checkstyle,output=$buildDir/reports/ktlint/ktlint-result.xml",
        ),
    )
    inputs.files(
        fileTree(mapOf("dir" to "src", "include" to "**/*.kt", "exclude" to "**/build/**")),
    )
    outputs.file(file("$buildDir/reports/ktlint-result.xml"))

    doLast {
        val outputFile = file("${project.buildDir}/reports/ktlint/ktlint-result.xml")

        if (outputFile.exists()) {
            println("Ktlint check completed. View detailed XML report at: $buildDir/reports/ktlint-result.xml")
            println("Saving Ktlint result to: ${outputFile.absolutePath}")
            println("Ktlint check results:")
            outputFile.forEachLine { println(it) }
        }
    }
}

tasks.named("preBuild") {
    dependsOn(ktlinCheck)
}
