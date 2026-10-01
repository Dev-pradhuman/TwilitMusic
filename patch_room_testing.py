import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'room-testing' not in content:
    content = content.replace('testImplementation(libs.junit)', 'testImplementation(libs.junit)\n    testImplementation("androidx.room:room-testing:2.6.1")')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
