import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

plugins {
    id("kotlin-kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("io.objectbox")
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.syarah.test.weatherapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.syarah.test.weatherapp"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}




dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.compose)
    implementation(libs.androidx.compose.graphics)
    implementation(libs.tooling.preview)
    implementation(libs.material3)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.test.manifest)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler.android)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertor)
    implementation(libs.okHttp)
    //room
/*    implementation ("androidx.room:room-ktx:2.5.0" )
    kapt ("androidx.room:room-compiler:2.5.0-alpha03")*/



    implementation(libs.objectbox)
    implementation(libs.objectbox.kotlin)
    implementation(libs.okhttp.interceptor)
//    annotationProcessor (libs.objectbox.processor)


//    implementation(libs.objectbox.browser)
    implementation (libs.timber)
    implementation(libs.viewmodel.compose)
    implementation(libs.hilt.navigation.compose)





}
