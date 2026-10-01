import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

old = '''                if (currentTrack != null) {
                    MiniPlayer(
                        track = currentTrack!!,
                        isPlaying = isPlaying,
                        onPlayPause = viewModel::playPause,
                        onClick = { showNowPlaying = true }
                    )
                }'''
new = '''                if (currentTrack != null) {
                    MiniPlayer(
                        track = currentTrack!!,
                        isPlaying = isPlaying,
                        progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                        onPlayPause = viewModel::playPause,
                        onClick = { showNowPlaying = true }
                    )
                }'''
content = content.replace(old, new)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
