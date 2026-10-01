import sys

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('val localProperties = java.util.Properties()', 'val localProperties = java.util.Properties()') # No, that won't work
content = content.replace('val localProperties = java.util.Properties()', 'val localProperties = Properties()')
content = content.replace('java.io.FileInputStream', 'FileInputStream')

if 'import java.util.Properties' not in content:
    content = 'import java.util.Properties\nimport java.io.FileInputStream\n' + content

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
