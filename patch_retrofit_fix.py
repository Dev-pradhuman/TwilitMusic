import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'retrofit2' not in content:
    content = content.replace('implementation(libs.media3.exoplayer)', 'implementation(libs.media3.exoplayer)\n    implementation("com.squareup.retrofit2:retrofit:2.11.0")\n    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")\n    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
