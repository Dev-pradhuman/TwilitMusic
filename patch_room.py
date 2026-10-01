import sys

with open('app/src/main/java/com/twilitmusic/app/data/local/TwilitDatabase.kt', 'r') as f:
    content = f.read()

content = content.replace('exportSchema = false', 'exportSchema = true')

with open('app/src/main/java/com/twilitmusic/app/data/local/TwilitDatabase.kt', 'w') as f:
    f.write(content)
