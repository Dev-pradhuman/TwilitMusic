import os
import re

def fix_file(filepath):
    if not os.path.exists(filepath): return
    with open(filepath, 'r') as f:
        content = f.read()

    # NowPlayingScreen / PlaylistDetailScreen
    content = content.replace('import androidx.compose.material.icons.filled.DragHandle\n', '')
    content = content.replace('Icons.Default.DragHandle', 'Icons.Default.Menu')
    
    # TrackItem
    content = content.replace('import androidx.compose.material.icons.filled.DownloadDone\n', '')
    content = content.replace('Icons.Filled.DownloadDone', 'Icons.Default.Check')
    
    # NowPlayingScreen removeAt / Reorderable
    content = content.replace('localQueue.toMutableList().apply {', 'localQueue.toMutableList().apply {') # Just mapping
    content = re.sub(r'add\(to\.index, removeAt\(from\.index\)\)', 'val item = this[from.index]; removeAt(from.index); add(to.index, item)', content)
    
    # size(...) missing import
    if 'Modifier.size(' in content and 'import androidx.compose.foundation.layout.size' not in content:
        content = 'import androidx.compose.foundation.layout.size\n' + content

    with open(filepath, 'w') as f:
        f.write(content)

base = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/'
for f in ['NowPlayingScreen.kt', 'PlaylistDetailScreen.kt', 'TrackItem.kt', 'SearchScreen.kt', 'MainViewModel.kt', 'theme/Theme.kt']:
    fix_file(base + f)

