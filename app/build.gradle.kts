import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.secret.gradle.plugin) // required for secrets.properties to work
    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

/// load secrets file
val secretsFile = rootProject.file("secrets.properties")
val secretsProp = Properties()
if (secretsFile.exists()) {
    secretsProp.load(secretsFile.reader())
} else {
    logger.warn("No secrets.properties found, using default or environment values")
}

android {
    namespace = "com.codemockup.ecommercecommunity"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ecommercecommunity"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        /// apply to all build types
        buildConfigField("String", "SENTRY_DSN", "\"${secretsProp.getProperty("SENTRY_DSN")}\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isCrunchPngs = false

            /// add aplication suffix for debug build
            /// allow to install both debug and release versions
            /// delete if not needed
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            /// for talsec HASH
            buildConfigField(
                "String",
                "SIGNING_HASH",
                "\"${secretsProp.getProperty("SIGNING_HASH_DEBUG")}\""
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            /// for talsec HASH
            buildConfigField(
                "String",
                "SIGNING_HASH",
                "\"${secretsProp.getProperty("SIGNING_HASH")}\""
            )
        }
    }

    bundle {
        language {
            enableSplit = true
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        @Suppress("DEPRECATION")
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    flavorDimensions += "environment"
    productFlavors {
        create("production") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_API_URL",
                "\"https://jellybellywikiapi.onrender.com/api/\""
            )
        }
        create("dev") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_API_URL",
                "\"https://jellybellywikiapi.onrender.com/api/\""
            )
        }
        create("staging") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_API_URL",
                "\"https://jellybellywikiapi.onrender.com/api/\""
            )
        }
    }

    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /// simplify permission request
    implementation(libs.accompanist.permissions)

    // reflect
    implementation(libs.kotlin.reflect)

    // network service retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    // coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
    // location service
    implementation(libs.play.services.location)

    // Sentry
    implementation(libs.sentry.android)
}