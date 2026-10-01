import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

import re

# find the Row inside ReorderableItem
old_row = r'(Column\(modifier = Modifier.weight\(1f\)\) \{\s*Text\(track.title\)\s*Text\(track.artist, style = MaterialTheme.typography.labelSmall\)\s*\})'
new_row = r'\1\n                        Box {\n                            var showMenu by remember { mutableStateOf(false) }\n                            IconButton(onClick = { showMenu = true }) { Icon(Icons.Default.MoreVert, "More") }\n                            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {\n                                DropdownMenuItem(text = { Text("Download") }, onClick = { showMenu = false })\n                                DropdownMenuItem(text = { Text("Add to Playlist") }, onClick = { showMenu = false })\n                            }\n                        }'

content = re.sub(old_row, new_row, content)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
