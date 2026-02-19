plugins {
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.zomato.photofilters"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
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
    lint {
        targetSdk = 36
        baseline = file("lint-baseline.xml")
    }
    testOptions {
        targetSdk = 36
    }
}
