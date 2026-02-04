plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.secondhand.market"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.secondhand.market"
        minSdk = 30
        targetSdk = 36
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
        compose = true
    }
}
//使用版本目录
//implementation(libs.androidx.core.ktx)
//等价于：implementation("androidx.core:core-ktx:版本号")
//这些依赖的定义在：gradle/libs.versions.toml
dependencies {
    implementation(libs.androidx.core.ktx)//kotlin扩展
    implementation(libs.androidx.lifecycle.runtime.ktx)//生命周期管理
    implementation(libs.androidx.activity.compose)//Compose Activity 支持
    implementation(platform(libs.androidx.compose.bom))//Compose BOM（物料清单）
    implementation(libs.androidx.compose.ui)//Compose UI 基础库
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)//Compose 预览工具
    implementation(libs.androidx.compose.material3)//Compose Material3 设计系统
    testImplementation(libs.junit) // 单元测试
    androidTestImplementation(libs.androidx.junit) // Android单元测试
    androidTestImplementation(libs.androidx.espresso.core) // UI测试
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)// Compose UI测试
    debugImplementation(libs.androidx.compose.ui.tooling)//Compose 预览工具
    debugImplementation(libs.androidx.compose.ui.test.manifest)// 测试清单
}