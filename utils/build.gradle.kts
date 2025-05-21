/*
 * build.gradle.kts - Module util
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
plugins {
    alias(libs.plugins.com.library)
    alias(libs.plugins.org.kotlin)
    alias(libs.plugins.com.hilt)
    alias(libs.plugins.com.ksp)
}

android {
    namespace = BuildConfig.UTIL_NAMESPACE
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK_VERSION
        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            isMinifyEnabled = Build.Release.isMinifyEnabled
            enableUnitTestCoverage = Build.Release.isDebuggable
        }
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = Build.Debug.isMinifyEnabled
            enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifecycle.libs)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.org.coroutines)
    implementation(libs.androidx.runner)
    implementation(libs.com.hilt)
    implementation(libs.test.hilt)
    ksp(libs.com.hilt.compiler)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support)
    implementation(libs.androidx.splashscreen)
    implementation(libs.bundles.androidx.navigation.libs)
    implementation(libs.com.material3)
    implementation(libs.nl.konfetti)
    implementation(libs.com.glide)
    ksp(libs.com.glide.compiler)
}
