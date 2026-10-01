import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.material.icons.filled.RepeatOne', 'import androidx.compose.material.icons.filled.RepeatOne\nimport androidx.compose.material.icons.filled.Favorite\nimport androidx.compose.material.icons.filled.FavoriteBorder')

old_sig = '''    val shuffleModeEnabled by viewModel.musicController.shuffleModeEnabled.collectAsState()
    val repeatMode by viewModel.musicController.repeatMode.collectAsState()'''
new_sig = '''    val shuffleModeEnabled by viewModel.musicController.shuffleModeEnabled.collectAsState()
    val repeatMode by viewModel.musicController.repeatMode.collectAsState()
    val isLiked by viewModel.isCurrentTrackLiked.collectAsState()'''
content = content.replace(old_sig, new_sig)

old_text = '''                Text(
                    track.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    track.artist,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(32.dp))'''
new_text = '''                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            track.title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            track.artist,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = { viewModel.toggleLike() }) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))'''
content = content.replace(old_text, new_text)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
