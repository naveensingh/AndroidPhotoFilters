plugins {
    alias(libs.plugins.android.application)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.filters"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.filters"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"
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
    lint {
        targetSdk = 36
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementation(project(":photofilterssdk"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
}
