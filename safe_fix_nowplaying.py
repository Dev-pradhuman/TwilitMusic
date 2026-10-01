import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt'
with open(path, 'r') as f:
    content = f.read()

# Remove Android imports
content = re.sub(r'import androidx\.compose\.ui\.res\.stringResource\n', '', content)
content = re.sub(r'import com\.twilitmusic\.app\.R\n', '', content)
content = re.sub(r'import androidx\.media3\..*\n', '', content)
content = re.sub(r'import androidx\.compose\.material\.icons\.filled\.DragHandle\n', '', content)

# Fix stringResource
content = re.sub(r'stringResource\([^\)]*\)', '""', content)

# Replace DragHandle with Menu
content = content.replace('Icons.Default.DragHandle', 'Icons.Default.Menu')

# Fix Media3 Player states
content = content.replace('Player.REPEAT_MODE_ONE', '1')
content = content.replace('Player.REPEAT_MODE_ALL', '2')
content = content.replace('Player.REPEAT_MODE_OFF', '0')

# Fix formatting
content = content.replace('String.format("%02d:%02d", minutes, seconds)', '"${minutes.toString().padStart(2, \'0\')}:${seconds.toString().padStart(2, \'0\')}"')

# Fix viewModel AudioPlayer methods
content = content.replace('viewModel.musicController.toggleShuffle()', 'viewModel.musicController.setShuffleModeEnabled(!viewModel.musicController.shuffleModeEnabled.value)')
content = content.replace('viewModel.musicController.cycleRepeatMode()', 'viewModel.musicController.setRepeatMode((viewModel.musicController.repeatMode.value + 1) % 3)')
content = content.replace('viewModel.musicController.position', 'viewModel.musicController.currentPosition')

# Remove Drag and Drop Reorderable code safely
queue_regex = r'LazyColumn\(.*?state = lazyListState.*?\{.*?\}\n\s*\}\n\s*\}'
simple_queue = '''LazyColumn {
                        items(localQueue.size) { index ->
                            val track = localQueue[index]
                            TrackItem(
                                track = track,
                                isPlaying = (track.id == currentTrack?.id),
                                onPlay = { viewModel.playQueue(localQueue, index) },
                                onRemove = { viewModel.removeTrack(index) }
                            )
                        }
                    }'''
content = re.sub(queue_regex, simple_queue, content, flags=re.DOTALL)

# Strip out drag states safely
content = re.sub(r'val lazyListState = rememberLazyListState\(\)\n\s*val state = rememberReorderableLazyListState.*?dragEndIndex = to\.index\n\s*\}', '', content, flags=re.DOTALL)
content = content.replace('var dragStartIndex by remember { mutableStateOf(-1) }', '')
content = content.replace('var dragEndIndex by remember { mutableStateOf(-1) }', '')

# add collectAsState and size import if needed
content = 'import androidx.compose.runtime.collectAsState\nimport androidx.compose.runtime.getValue\nimport androidx.compose.runtime.setValue\n' + content

with open(path, 'w') as f:
    f.write(content)
