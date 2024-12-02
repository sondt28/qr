plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)

}

android {
    namespace = "com.son.qrscan"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.son.qrscan"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
    flavorDimensions += "version"

    productFlavors {
        create("appDev") {
            dimension = "version"
            manifestPlaceholders["app_id_admod"] = "ca-app-pub-3940256099942544~3347511713"
        }
        create("appProd") {
            dimension = "version"
            manifestPlaceholders["app_id_admod"] = "ca-app-pub-3940256099942544~3347511713"
        }
    }
}

dependencies {
    implementation(project(":common"))
    ksp(libs.hilt.compiler)
    implementation("com.google.android.gms:play-services-ads:23.3.0")
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}