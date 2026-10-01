import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

# Enable buildConfig
if 'buildConfig = true' not in content:
    build_features = 'buildFeatures {\n        compose = true\n        buildConfig = true\n    }'
    if 'buildFeatures {' in content:
        content = content.replace('buildFeatures {\n        compose = true\n    }', build_features)
    else:
        content = content.replace('android {', 'android {\n    ' + build_features)

# Read JAMENDO_CLIENT_ID from local.properties
local_prop_code = '''
import java.util.Properties
import java.io.FileInputStream

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}
val jamendoClientId = localProperties.getProperty("JAMENDO_CLIENT_ID")?.replace("\\"", "") ?: ""
'''

if 'val localProperties' not in content:
    content = local_prop_code + '\n' + content

build_config_field = '''        buildConfigField("String", "JAMENDO_CLIENT_ID", "\\"${jamendoClientId}\\"")'''

if 'buildConfigField' not in content:
    content = content.replace('defaultConfig {', 'defaultConfig {\n' + build_config_field)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
