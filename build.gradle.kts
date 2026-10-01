// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.ksp) apply false
}
