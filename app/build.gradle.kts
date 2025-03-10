plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.casino"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.casino"
        minSdk = 29
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.7")

    //Pager
    implementation ("com.google.accompanist:accompanist-pager:0.12.0")

    // Lottie
    implementation("com.airbnb.android:lottie-compose:6.6.2")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.0")

    // ConstraintLayout Dependencies
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")

//    //Github
//    implementation ("com.github.JhDroid:android-roulette-wheel-view:1.0.3")

//    // CameraX
//    implementation("androidx.camera:camera-camera2:1.4.1")
//    implementation("androidx.camera:camera-lifecycle:1.4.1")
//    implementation("androidx.camera:camera-view:1.4.1")
//
//    // Zxing
//    implementation("com.google.zxing:core:3.3.3")
}