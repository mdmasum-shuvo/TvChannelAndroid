plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    // Add the Performance Monitoring Gradle plugin
    id("com.google.firebase.firebase-perf")

}

android {
    namespace = "com.appifly.tvchannel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.appifly.tvchannel"
        minSdk = 26
        targetSdk = 34
        versionCode = 11
        versionName = "1.0.9"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            //signingConfig = signingConfigs.getByName("debug")
            //signingConfig = signingConfigs.getByName("debug")
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += listOf("firebase")
    productFlavors {
        create("dev") {
            dimension = "firebase"
            applicationId = "com.appifly.tvchannel.dev"
            
            buildConfigField("String","BANNER_ADD_ID","\"" + "ca-app-pub-3940256099942544/6300978111"+ "\"")
            buildConfigField("String","INTERSTITIAL_ADD_ID","\"" + "ca-app-pub-3940256099942544/1033173712"+ "\"")
            buildConfigField("String","FB_BANNER_ADD_ID","\"" + "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"+ "\"")
            buildConfigField("String","FB_INTERSTITIAL_ADD_ID","\"" + "VID_HD_16_9_46S_APP_INSTALL#YOUR_PLACEMENT_ID"+ "\"")
            

        }
        create("pro") {
            dimension = "firebase"
            applicationId = "com.appifly.tvchannel"
            buildConfigField("String","BANNER_ADD_ID","\"" + "ca-app-pub-1337577089653332/5737408144"+ "\"")
            buildConfigField("String","INTERSTITIAL_ADD_ID","\"" + "ca-app-pub-1337577089653332/4285892965"+ "\"")
            buildConfigField("String","FB_BANNER_ADD_ID","\"" + "785679336855305_785683446854894"+ "\"")
            buildConfigField("String","FB_INTERSTITIAL_ADD_ID","\"" + "785679336855305_785683713521534"+ "\"")

        }
    }
    buildFeatures {
        buildConfig = true
    }

}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.media3:media3-exoplayer-hls:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val mediaVersion="1.0.1"
    implementation ("androidx.media3:media3-exoplayer:$mediaVersion")
    implementation ("androidx.media3:media3-ui:$mediaVersion")
    implementation ("androidx.media3:media3-exoplayer-dash:$mediaVersion")
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra.get("version_hilt_navigation")}")

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

    // Moshi
    implementation("com.squareup.moshi:moshi:${rootProject.extra.get("version_moshi")}")
    implementation("com.squareup.moshi:moshi-kotlin:${rootProject.extra.get("version_moshi")}")
    implementation("com.squareup.moshi:moshi-adapters:${rootProject.extra.get("version_moshi")}")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation(project(":app_data_source"))
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.1.0")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    // Add the dependency for the Performance Monitoring library
    implementation("com.google.firebase:firebase-perf")
    implementation("com.google.firebase:firebase-messaging")
    implementation ("androidx.core:core-splashscreen:1.0.1")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.20.0")
    //admob
    implementation("com.google.android.gms:play-services-ads:23.0.0")
    //WindowSizeClass
    implementation ("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation ("com.google.android.play:app-update:${rootProject.extra.get("appUpdateVersion")}")
    implementation ("com.google.android.play:app-update-ktx:${rootProject.extra.get("appUpdateVersion")}")

    implementation( "com.google.accompanist:accompanist-pager:0.23.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.23.1")
    implementation("com.facebook.android:audience-network-sdk:6.16.0")
    implementation ("com.facebook.infer.annotation:infer-annotation:0.18.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

