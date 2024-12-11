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

    implementation (libs.glide)

    implementation (libs.barcode.scanning)
    implementation (libs.androidx.camera.mlkit.vision)
    implementation(libs.guava)
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.video)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.extensions)

    implementation(libs.lottie)

    implementation(libs.play.services.ads)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}