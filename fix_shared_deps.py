import re

path = 'shared/build.gradle.kts'
with open(path, 'r') as f:
    content = f.read()

deps = '''        androidMain.dependencies {
            implementation(libs.media3.exoplayer)
            implementation(libs.media3.session)
            implementation(libs.media3.common)
        }'''
content = content.replace('androidMain.dependencies {', deps.strip())
with open(path, 'w') as f:
    f.write(content)
