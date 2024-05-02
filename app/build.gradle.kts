plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.notes_app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.notes_app"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    //implementation("androidx.appcompat:design:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //creating database or room for database

    //implementation ("androidx.room:room-runtime:2.5.2")
    //annotationProcessor("androidx.room:room-compiler:2.2.5")

    //recycler view
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    //scalable size unit
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    //matrial desin
    implementation("com.google.android.material:material:1.9.0")

    //rounded imageview
    implementation("com.makeramen:roundedimageview:2.3.0")

    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-ktx:2.4.2")
    implementation ("androidx.room:room-paging:2.4.2")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha15")

}