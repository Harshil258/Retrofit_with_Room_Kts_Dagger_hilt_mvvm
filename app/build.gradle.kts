plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.harshil.retrofitwithroomkts"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.harshil.retrofitwithroomkts"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Enabling view binding for easy binding of views
    buildFeatures {
        viewBinding = true
    }

    // Configure Java version compatibility
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Kotlin JVM target version
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // AndroidX dependencies
    implementation(libs.androidx.core.ktx) // Android Core extensions
    implementation(libs.androidx.appcompat) // AppCompat for backward compatibility
    implementation(libs.material) // Material Components for UI
    implementation(libs.androidx.activity) // Activity-related features
    implementation(libs.androidx.constraintlayout) // ConstraintLayout for flexible layouts

    // Testing dependencies
    testImplementation(libs.junit) // Unit testing framework
    androidTestImplementation(libs.androidx.junit) // AndroidJUnit testing library
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing

    // Room dependencies (for local database)
    implementation(libs.androidx.room.ktx) // Room extensions for Kotlin
    implementation(libs.androidx.room.runtime) // Room runtime library
    ksp(libs.androidx.room.compiler) // Room compiler (KSP version)

    // Lifecycle dependencies for ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel extensions
    implementation(libs.androidx.lifecycle.livedata.ktx) // LiveData extensions

    // ViewModel KTX for Activity
    implementation(libs.androidx.activity.ktx) // KTX extensions for Activity

    // Hilt for dependency injection
    implementation(libs.hilt.android) // Hilt Android DI
    ksp(libs.hilt.android.compiler) // Hilt compiler (KSP version)
    ksp(libs.androidx.hilt.compiler) // Hilt compiler for AndroidX

    // Retrofit dependencies (for network operations)
    implementation(libs.gson) // Gson converter for Retrofit
    implementation(libs.retrofit) // Retrofit library for network requests
    implementation(libs.converter.gson) // Gson converter for Retrofit
}
