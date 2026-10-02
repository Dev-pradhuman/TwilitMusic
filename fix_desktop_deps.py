import re

path = 'shared/build.gradle.kts'
with open(path, 'r') as f:
    content = f.read()

deps = '''        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.cio)
                implementation("uk.co.caprica:vlcj:4.8.2")
            }
        }'''
content = re.sub(r'val desktopMain by getting \{\n\s*dependencies \{\n\s*implementation\(compose\.desktop\.currentOs\)\n\s*implementation\(libs\.ktor\.client\.cio\)\n\s*\}\n\s*\}', deps, content, flags=re.DOTALL)

with open(path, 'w') as f:
    f.write(content)
