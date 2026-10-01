import os
import glob
import re

ui_dir = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui'

for root, _, files in os.walk(ui_dir):
    for f in files:
        if not f.endswith('.kt'): continue
        filepath = os.path.join(root, f)
        with open(filepath, 'r') as file:
            content = file.read()

        # Fix stringResource
        content = re.sub(r'stringResource\([^\)]*\)', '""', content)
        content = re.sub(r'import androidx\.compose\.ui\.res\.stringResource\n?', '', content)
        content = re.sub(r'import com\.twilitmusic\.app\.R\n?', '', content)
        
        # AsyncImage is from coil3 now
        content = content.replace('import coil.compose.AsyncImage', 'import coil3.compose.AsyncImage')
        
        with open(filepath, 'w') as file:
            file.write(content)

# Fix Theme.kt
theme_path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/theme/Theme.kt'
if os.path.exists(theme_path):
    with open(theme_path, 'r') as f:
        theme = f.read()
    
    theme = re.sub(r'import android\..+\n', '', theme)
    theme = re.sub(r'import androidx\.core\.view\..+\n', '', theme)
    theme = re.sub(r'import androidx\.compose\.ui\.platform\.LocalContext\n', '', theme)
    theme = re.sub(r'import androidx\.compose\.ui\.platform\.LocalView\n', '', theme)
    
    theme = re.sub(r'val dynamicColor = Build\.VERSION\.SDK_INT >= Build\.VERSION_CODES\.S', 'val dynamicColor = false', theme)
    theme = re.sub(r'dynamicColor && dynamicDarkColorScheme\(LocalContext\.current\)', 'false', theme)
    theme = re.sub(r'dynamicColor && dynamicLightColorScheme\(LocalContext\.current\)', 'false', theme)
    
    theme = re.sub(r'val view = LocalView\.current\n\s+if \(!view\.isInEditMode\) \{\n\s+SideEffect \{\n\s+val window = \(view\.context as Activity\)\.window\n\s+window\.statusBarColor = colorScheme\.primary\.toArgb\(\)\n\s+WindowCompat\.getInsetsController\(window, view\)\.isAppearanceLightStatusBars = darkTheme\n\s+\}\n\s+\}', '', theme)
    
    with open(theme_path, 'w') as f:
        f.write(theme)
