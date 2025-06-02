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
                    "import-ordering,trailing-comma-on-call-site," + // Requires trailing comma in multi-line calls
                    "trailing-comma-on-declaration-site," + // Requires trailing comma in lists
                    "trailing-comma-on-call-site," + // Requires trailing comma in multi-line calls
                    "no-empty-first-line-in-class-body," + // Prohibits empty first line in class body
                    "no-empty-first-line-in-method-block," + // Prohibits empty first line in method block
                    "argument-list-wrapping," + // Wraps argument lists
                    "multiline-if-else," + // Requires multiline if-else
                    "spacing-between-declarations-with-comments," + // Requires spacing between declarations with comments
                    "indent," + // Indents code
                    "annotation," + // Requires annotations
                    "annotation-spacing," + // Requires spacing around annotations
                    "modifier-list-spacing," + // Requires spacing around modifiers
                    "spacing-between-declarations-with-annotations," + // Requires spacing between declarations with annotations
                    "wrapping," + // Line break rules
                    "comment-wrapping," + // Line break rules for comments
                    "spacing-around-angle-brackets," + // Requires spacing around angle brackets
                    "no-unused-imports," + // Prohibits unused imports
                    "no-trailing-spaces," + // Prohibits trailing spaces
                    "class-naming," + // Requires class naming
                    "package-name," + // Requires package name
                    "paren-spacing," + // Requires spacing around parentheses
                    "function-signature," + // Requires function signature
                    "block-comment-initial-star-alignment," + // Requires block comment initial star alignment
                    "chain-wrapping," + // Wraps chains
                    "no-blank-line-before-rbrace," + // Prohibits blank line before rbrace
                    "chain-wrapping," + // Wraps chains
                    "no-consecutive-blank-lines," + // Prohibits consecutive blank lines
                    "enum-entry-name-case," + // Requires enum entry name case in higher case
                    "filename," + // Requires filename
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
