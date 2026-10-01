import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'kotlinx-serialization-json' not in content:
    content = content.replace('implementation(libs.androidx.navigation.compose)', 'implementation(libs.androidx.navigation.compose)\n    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)

with open('build.gradle.kts', 'r') as f:
    root_content = f.read()

if 'plugin.serialization' not in root_content:
    root_content = root_content.replace('plugins {', 'plugins {\n    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false')

with open('build.gradle.kts', 'w') as f:
    f.write(root_content)

with open('app/build.gradle.kts', 'r') as f:
    content2 = f.read()

if 'plugin.serialization' not in content2:
    content2 = content2.replace('alias(libs.plugins.jetbrainsKotlinAndroid)', 'alias(libs.plugins.jetbrainsKotlinAndroid)\n    id("org.jetbrains.kotlin.plugin.serialization")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content2)
