with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

imports = 'import androidx.compose.runtime.collectAsState\nimport androidx.compose.runtime.getValue\nimport androidx.compose.runtime.setValue\n'
content = content.replace(imports, '') # remove from start
content = content.replace('package com.twilitmusic.app.ui\n', 'package com.twilitmusic.app.ui\n' + imports)

with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
