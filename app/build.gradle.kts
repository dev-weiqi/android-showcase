import ApplicationConfig.versionCode
import ApplicationConfig.versionName
import GradlePluginId.androidApplication
import GradlePluginId.kotlinAndroid
import GradlePluginId.kotlinAndroidExtensions
import com.android.build.gradle.AndroidConfig
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    id(GradlePluginId.androidApplication)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdkVersion)

    defaultConfig {
        applicationId = ApplicationConfig.id
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
        buildToolsVersion(ApplicationConfig.buildToolsVersion)

        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = ApplicationConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")

        }

        getByName("debug") {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.isReturnDefaultValues
        }
    }

    dependencies {
        //module
        implementation(project(ModuleDependency.featureBase))
        implementation(project(ModuleDependency.featureAlbum))

        //core
        implementation(LibraryDependency.kotlin)
        implementation(LibraryDependency.timber)
        implementation(LibraryDependency.fuelAndroid)
        implementation(LibraryDependency.fuelCoroutines)
        implementation(LibraryDependency.fuelGson)
        implementation(LibraryDependency.supportAppCompact)
        implementation(LibraryDependency.supportConstraintLayout)
        implementation(LibraryDependency.supportRecyclerView)
        implementation(LibraryDependency.supportMaterial)
        implementation(LibraryDependency.coreKtx)
        implementation(LibraryDependency.fragmentKtx)
        implementation(LibraryDependency.lifecycleViewModelKtx)

        //test
        testImplementation(LibraryDependency.junit)
        androidTestImplementation(LibraryDependency.testRunner)
        androidTestImplementation(LibraryDependency.espressoCore)
        testImplementation(LibraryDependency.kluent)
        androidTestImplementation(LibraryDependency.kluentAndroid)
        testImplementation(LibraryDependency.mockitoInline)
        androidTestImplementation(LibraryDependency.mockitoAndroid)
        testImplementation(LibraryDependency.mockitoKotlin)
    }
}
