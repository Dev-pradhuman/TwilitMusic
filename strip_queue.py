import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt'
with open(path, 'r') as f:
    content = f.read()

# Replace everything from `val lazyListState` to `Scaffold(`
content = re.sub(r'var dragStartIndex.*?Scaffold\(', 'Scaffold(', content, flags=re.DOTALL)

# Replace the LazyColumn inside Queue tab.
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

with open(path, 'w') as f:
    f.write(content)

