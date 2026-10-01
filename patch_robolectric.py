import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'robolectric' not in content:
    content = content.replace('testImplementation(libs.junit)', 'testImplementation(libs.junit)\n    testImplementation("org.robolectric:robolectric:4.11.1")\n    testImplementation("androidx.test:core:1.5.0")')
if 'testOptions' not in content:
    content = content.replace('buildTypes {', 'testOptions {\n        unitTests {\n            isIncludeAndroidResources = true\n        }\n    }\n\n    buildTypes {')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
