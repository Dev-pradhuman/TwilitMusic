import sys

with open('README.md', 'r') as f:
    content = f.read()

old_features = '''## Features
- **Modern UI**: Built entirely with Jetpack Compose using a custom dark-first "Twilight" theme.
- **Demo Tracks**: Includes 20 royalty-free tracks for immediate testing.
- **Audio Playback**: Uses Media3 ExoPlayer and MediaSessionService for robust background playback.
- **Now Playing**: Full-screen player with working play/pause, next/previous, and queue management.
- **Mini Player**: Persistent mini player that docks above the bottom navigation bar.
- **Queue Management**: Add, remove, and reorder tracks in the current queue using drag-and-drop.'''
new_features = '''## Features
- **Modern UI**: Built entirely with Jetpack Compose using a custom dark-first "Twilight" theme.
- **Demo Tracks**: Includes 20 royalty-free tracks for immediate testing.
- **Audio Playback**: Uses Media3 ExoPlayer and MediaSessionService for robust background playback.
- **Now Playing**: Full-screen player with working play/pause, next/previous, and queue management.
- **Mini Player**: Persistent mini player that docks above the bottom navigation bar.
- **Queue Management**: Add, remove, and reorder tracks in the current queue using drag-and-drop.
- **Seek Bar**: Draggable slider to seek within the track with live progress.
- **Playback Controls**: Shuffle and repeat (off / all / one) modes.
- **Search**: Debounced track search by title or artist.
- **Library**: Tabs for Playlists, Liked tracks, and Play History.
- **Persistence**: Room database for Liked tracks, Playlists, and Play history, plus SharedPreferences for restoring queue state on restart.'''
content = content.replace(old_features, new_features)

if '## Phase 2' not in content:
    content += '''\n## Phase 2
- Implemented working seek bar.
- Added Shuffle and Repeat modes.
- Queue persistence across app restarts.
- Room database added for library features.
- Search and Library screens implemented with real data.
'''

with open('README.md', 'w') as f:
    f.write(content)
