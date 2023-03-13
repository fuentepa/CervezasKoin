
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.paf.cervezaskoin.data"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.jetbrains.kotlin.coroutines.android)
    implementation(libs.arrow.core)
    //implementation(libs.jetbrains.kotlin.serialization)
    //implementation(libs.jetbrains.kotlin.serialization.json)
    implementation(libs.google.code.gson)
}
