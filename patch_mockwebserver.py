import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'mockwebserver' not in content:
    content = content.replace('testImplementation(libs.junit)', 'testImplementation(libs.junit)\n    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
