import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

imports = [
    'import androidx.compose.material3.DropdownMenu',
    'import androidx.compose.material3.DropdownMenuItem',
    'import androidx.compose.runtime.remember',
    'import androidx.compose.runtime.mutableStateOf',
    'import androidx.compose.runtime.getValue',
    'import androidx.compose.runtime.setValue'
]

for imp in imports:
    if imp not in content:
        content = imp + '\n' + content

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
