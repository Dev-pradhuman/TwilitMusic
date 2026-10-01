import re

path = 'androidApp/build.gradle.kts'
with open(path, 'r') as f:
    content = f.read()

# Plugins
content = re.sub(r'alias\(libs\.plugins\.hilt\)\n', '', content)
content = re.sub(r'alias\(libs\.plugins\.kapt\)\n', '', content)

# Dep Hilt
content = re.sub(r'implementation\(libs\.hilt\.android\)\n', '', content)
content = re.sub(r'kapt\(libs\.hilt\.compiler\)\n', '', content)
content = re.sub(r'implementation\(libs\.androidx\.hilt\.navigation\.compose\)\n', '', content)

# Dependencies
# Coil
content = re.sub(r'implementation\(libs\.coil\.compose\)\n', 'implementation(libs.coil3.compose)\n', content)

# Koin
content += "\n    implementation(libs.koin.android)\n"

# KSP version warning? I'm using Compose plugin now!
content = re.sub(r'composeOptions \{.*?\}\n', '', content, flags=re.DOTALL)
content = re.sub(r'id\("org\.jetbrains\.kotlin\.plugin\.serialization"\)', 'alias(libs.plugins.kotlinSerialization)', content)

with open(path, 'w') as f:
    f.write(content)

