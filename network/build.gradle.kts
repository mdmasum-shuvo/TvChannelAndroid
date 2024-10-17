plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.appifly.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        buildConfigField(
            "String",
            "BASE_URL",
            "\"" + "https://laraveltrust.com/public/api/" + "\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            consumerProguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit: ${rootProject.extra.get("version_retrofit")}")
    implementation("com.squareup.retrofit2:converter-moshi:${rootProject.extra.get("version_retrofit")}")
    implementation(
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${
            rootProject.extra.get(
                "version_retrofit_coroutines_adapt"
            )
        }"
    )
    implementation(project(":cachemanager"))
    // Moshi
    implementation("com.squareup.moshi:moshi:${rootProject.extra.get("version_moshi")}")
    implementation("com.squareup.moshi:moshi-kotlin:${rootProject.extra.get("version_moshi")}")
    implementation("com.squareup.moshi:moshi-adapters:${rootProject.extra.get("version_moshi")}")
    //OkHttp logging
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")

}