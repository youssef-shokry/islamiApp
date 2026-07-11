plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.route.islamie_app101"
    compileSdk = 36

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.route.islamie_app101"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //Android Dependancies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Splash Screen Dependency
    implementation(libs.androidx.core.splashscreen)
    //Navigation Component Dependency
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    //Kotlin serialization plugin
    implementation(libs.kotlinx.serialization.json)
    //ViewPager2 Dependency
    implementation(libs.androidx.viewpager2)
    //Kotlin Coroutines Dependency
    implementation(libs.kotlinx.coroutines.android)
    //ViewModel Dependency
    implementation(libs.androidx.lifecycle.viewmodel)
    //LiveData Dependency
    implementation(libs.androidx.lifecycle.livedata)
    //Retrofit
    implementation(libs.retrofit)
    //Retrifit Gson Converter
    implementation(libs.converter.gson)
}