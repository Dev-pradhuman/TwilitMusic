import re
with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

# Replace the Scaffold and LazyColumn block for QueueSheet exactly
old_block = r'Scaffold\(\s*topBar = \{\s*TopAppBar\(\s*title = \{ Text\(""\) \},\s*navigationIcon = \{\s*IconButton\(onClick = onClose\) \{\s*Icon\(Icons\.Default\.KeyboardArrowDown, ""\)\s*\}\s*\}\s*\)\s*\}\s*\) \{ padding ->\s*LazyColumn \{\s*items\(localQueue\.size\) \{ index ->\s*val track = localQueue\[index\]\s*TrackItem\(\s*track = track,\s*isPlaying = \(track\.id == currentTrack\?\.id\),\s*onPlay = \{ viewModel\.playQueue\(localQueue, index\) \},\s*onRemove = \{ viewModel\.removeTrack\(index\) \}\s*\)\s*\}\s*\}\s*\}.*?\}'

new_block = '''Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Queue") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.KeyboardArrowDown, "Close Queue")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(localQueue.size) { index ->
                val track = localQueue[index]
                TrackItem(
                    track = track,
                    isPlaying = (track.id == currentTrack?.id),
                    onPlay = { viewModel.playQueue(localQueue, index) },
                    onRemove = { viewModel.removeTrack(index) }
                )
            }
        }
    }
}'''

content = re.sub(old_block, new_block, content, flags=re.DOTALL)
with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
