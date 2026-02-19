plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.zomato.photofilters"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        targetSdk = 34
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

    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }
}
