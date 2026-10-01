import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.ui.res.stringResource', 'import androidx.compose.ui.res.stringResource\nimport androidx.compose.material3.LinearProgressIndicator\nimport androidx.compose.foundation.layout.Box')

old_main = '''    val isPlaying by viewModel.musicController.isPlaying.collectAsState()'''
new_main = '''    val isPlaying by viewModel.musicController.isPlaying.collectAsState()
    val position by viewModel.musicController.position.collectAsState()
    val duration by viewModel.musicController.duration.collectAsState()'''
content = content.replace(old_main, new_main)

old_mini = '''                    currentTrack?.let { track ->
                        MiniPlayer(
                            track = track,
                            isPlaying = isPlaying,
                            onPlayPause = { viewModel.playPause() },
                            onClick = { showNowPlaying = true }
                        )
                    }'''
new_mini = '''                    currentTrack?.let { track ->
                        MiniPlayer(
                            track = track,
                            isPlaying = isPlaying,
                            progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                            onPlayPause = { viewModel.playPause() },
                            onClick = { showNowPlaying = true }
                        )
                    }'''
content = content.replace(old_mini, new_mini)

old_mini_fun = '''fun MiniPlayer(
    track: Track,
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),'''
new_mini_fun = '''fun MiniPlayer(
    track: Track,
    isPlaying: Boolean,
    progress: Float,
    onPlayPause: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),'''
content = content.replace(old_mini_fun, new_mini_fun)

old_mini_end = '''                )
            }
        }
    }
}'''
new_mini_end = '''                )
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                trackColor = androidx.compose.ui.graphics.Color.Transparent
            )
        }
    }
}'''
content = content.replace(old_mini_end, new_mini_end)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
