import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

# find TrackItem function and remove it
import re
content = re.sub(r'@Composable\s*fun TrackItem\(.*?\)\s*\{.*?\n\}', '', content, flags=re.DOTALL)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
