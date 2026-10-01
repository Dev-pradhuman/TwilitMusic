import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.ui.res.stringResource', 'import androidx.compose.ui.res.stringResource\nimport androidx.media3.common.Player')

old_sig = '''    val duration by viewModel.musicController.duration.collectAsState()
    var sliderPosition by remember { mutableStateOf<Float?>(null) }
    val displayPosition = sliderPosition ?: position.toFloat()'''
new_sig = '''    val duration by viewModel.musicController.duration.collectAsState()
    var sliderPosition by remember { mutableStateOf<Float?>(null) }
    val displayPosition = sliderPosition ?: position.toFloat()
    
    val shuffleModeEnabled by viewModel.musicController.shuffleModeEnabled.collectAsState()
    val repeatMode by viewModel.musicController.repeatMode.collectAsState()'''
content = content.replace(old_sig, new_sig)

old_controls = '''                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onPrev) {
                        Icon(Icons.Default.SkipPrevious, stringResource(R.string.previous), modifier = Modifier.size(48.dp))
                    }
                    FloatingActionButton(onClick = onPlayPause) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) stringResource(R.string.pause) else stringResource(R.string.play)
                        )
                    }
                    IconButton(onClick = onNext) {
                        Icon(Icons.Default.SkipNext, stringResource(R.string.next), modifier = Modifier.size(48.dp))
                    }
                }'''
new_controls = '''                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.musicController.toggleShuffle() }) {
                        Icon(
                            imageVector = Icons.Default.Shuffle,
                            contentDescription = "Shuffle",
                            tint = if (shuffleModeEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = onPrev) {
                        Icon(Icons.Default.SkipPrevious, stringResource(R.string.previous), modifier = Modifier.size(48.dp))
                    }
                    FloatingActionButton(onClick = onPlayPause) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) stringResource(R.string.pause) else stringResource(R.string.play)
                        )
                    }
                    IconButton(onClick = onNext) {
                        Icon(Icons.Default.SkipNext, stringResource(R.string.next), modifier = Modifier.size(48.dp))
                    }
                    IconButton(onClick = { viewModel.musicController.cycleRepeatMode() }) {
                        val icon = if (repeatMode == Player.REPEAT_MODE_ONE) Icons.Default.RepeatOne else Icons.Default.Repeat
                        val tint = if (repeatMode != Player.REPEAT_MODE_OFF) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        Icon(
                            imageVector = icon,
                            contentDescription = "Repeat",
                            tint = tint
                        )
                    }
                }'''
content = content.replace(old_controls, new_controls)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
