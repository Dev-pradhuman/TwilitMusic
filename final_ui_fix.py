import re

# TrackItem
path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/TrackItem.kt'
if True:
    with open(path, 'r') as f:
        content = f.read()
    content = content.replace('Icons.Filled.DownloadDone', 'Icons.Default.Check')
    with open(path, 'w') as f:
        f.write(content)

# NowPlayingScreen
path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt'
if True:
    with open(path, 'r') as f:
        content = f.read()
    content = re.sub(r'import androidx\.media3\..+\n', '', content)
    content = content.replace('String.format("%02d:%02d", minutes, seconds)', '"${minutes.toString().padStart(2, \'0\')}:${seconds.toString().padStart(2, \'0\')}"')
    content = content.replace('viewModel.musicController.toggleShuffle()', 'viewModel.musicController.setShuffleModeEnabled(!viewModel.musicController.shuffleModeEnabled.value)')
    content = content.replace('viewModel.musicController.position', 'viewModel.musicController.currentPosition')
    
    # Repeat Mode Logic
    content = content.replace('Player.REPEAT_MODE_ONE', '1')
    content = content.replace('Player.REPEAT_MODE_ALL', '2')
    content = content.replace('Player.REPEAT_MODE_OFF', '0')
    content = content.replace('viewModel.musicController.cycleRepeatMode()', 'viewModel.musicController.setRepeatMode((viewModel.musicController.repeatMode.value + 1) % 3)')
    
    # TrackItem parameters in NowPlayingScreen
    # Wait, TrackItem signature was TrackItem(track, onClick, modifier, trailingIcon). I changed it to (track, isPlaying, onPlay, onRemove). I should revert TrackItem params to onClick.
    # No, TrackItem has default parameters. Let's look at TrackItem later. 
    # For now, let's fix the TrackItem call in NowPlayingScreen to use standard params.
    content = re.sub(r'TrackItem\(\s*track = track,\s*isPlaying = .*?,\s*onPlay = .*?,\s*onRemove = .*?\s*\)', 'TrackItem(track = track, onClick = { viewModel.playQueue(localQueue, index) })', content, flags=re.DOTALL)
    
    with open(path, 'w') as f:
        f.write(content)

# MainViewModel
path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainViewModel.kt'
if True:
    with open(path, 'r') as f:
        content = f.read()
    # The syntax error was on line 120, let's just make sure it's valid.
    content = re.sub(r'\n\}\n\}$', '\n}\n', content) # Ensure single trailing brace
    
    with open(path, 'w') as f:
        f.write(content)

