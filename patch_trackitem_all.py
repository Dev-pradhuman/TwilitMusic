import sys

def patch_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Generic replace for NowPlayingScreen
    import re
    if 'NowPlayingScreen' in filepath:
        old_row = r'Row\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.clickable \{ onPlayTrack\(track\) \}\s*\.padding\(16\.dp\),\s*verticalAlignment = Alignment\.CenterVertically\s*\)\s*\{.*?\}'
        content = re.sub(old_row, 'TrackItem(track = track, onClick = { onPlayTrack(track) })', content, flags=re.DOTALL)
    
    # Generic replace for SearchScreen
    if 'SearchScreen' in filepath:
        old_row = r'Row\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.clickable \{ onTrackClick\(track\) \}\s*\.padding\(16\.dp\),\s*verticalAlignment = Alignment\.CenterVertically\s*\)\s*\{.*?\}'
        content = re.sub(old_row, 'TrackItem(track = track, onClick = { onTrackClick(track) })', content, flags=re.DOTALL)

    # Generic replace for LibraryScreen
    if 'LibraryScreen' in filepath:
        old_row = r'Row\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.clickable \{ onPlayTrack\(track\) \}\s*\.padding\(16\.dp\),\s*verticalAlignment = Alignment\.CenterVertically\s*\)\s*\{.*?\}'
        content = re.sub(old_row, 'TrackItem(track = track, onClick = { onPlayTrack(track) })', content, flags=re.DOTALL)

    with open(filepath, 'w') as f:
        f.write(content)

patch_file('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt')
patch_file('app/src/main/java/com/twilitmusic/app/ui/SearchScreen.kt')
patch_file('app/src/main/java/com/twilitmusic/app/ui/LibraryScreen.kt')
