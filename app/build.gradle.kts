@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.paf.cervezaskoin"
    compileSdk = 33
    buildToolsVersion = "33.0.1"

    defaultConfig {
        applicationId = "com.paf.cervezaskoin"
        minSdk = 26
        targetSdk  = 33
        versionCode  = 1
        versionName  = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation( project(":domain"))
    implementation( project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.jetbrains.kotlin.serialization)
    implementation(libs.jetbrains.kotlin.serialization.json)
    implementation(libs.jetbrains.kotlin.coroutines.android)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.arrow.core)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.glide.glide)
    annotationProcessor(libs.glide.compiler)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}