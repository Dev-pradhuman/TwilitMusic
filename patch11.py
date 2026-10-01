import sys, re

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

pattern = re.compile(r'Row\(\s*modifier = Modifier.fillMaxWidth\(\),\s*horizontalArrangement = Arrangement.SpaceEvenly,\s*verticalAlignment = Alignment.CenterVertically\s*\)\s*\{.*?IconButton\(onClick = onNext\) \{.*?Icon\(Icons.Default.SkipNext.*?\}\s*\}', re.DOTALL)

new_str = '''Row(
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

content = pattern.sub(new_str, content)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
