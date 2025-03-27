plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
}

android {
    namespace = "fpoly.phongndtph56750.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "fpoly.phongndtph56750.myapplication"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}
    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
        }
    }

    dependencies {

        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.database)
        implementation(libs.firebase.auth)
        implementation(libs.glide)
        implementation(libs.material.dialogs)
        implementation(libs.circleimageview)
        implementation(libs.circleindicator)
        implementation(libs.gson)
        implementation(libs.eventbus)
        implementation(libs.exoplayer)
        implementation(libs.flowlayout)
        implementation(libs.paypal)
        implementation(libs.zxing.core)
        implementation(libs.zxing.embedded)

        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
//    implementation(libs.appcompat)
//    implementation(libs.material)
//    implementation(libs.activity)
//    implementation(libs.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.ext.junit)
//    androidTestImplementation(libs.espresso.core)
    }
