import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'media3-database' not in content:
    content = content.replace('implementation(libs.androidx.media3.exoplayer)', 'implementation(libs.androidx.media3.exoplayer)\n    implementation("androidx.media3:media3-database:1.3.0")\n    implementation("androidx.media3:media3-datasource:1.3.0")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
