with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    lines = f.readlines()

new_lines = []
for line in lines:
    if line.strip() == 'TrackItem(track = track, onClick = { viewModel.playQueue(localQueue, index) }) }':
        new_lines.append('                TrackItem(track = track, onClick = { viewModel.playQueue(localQueue, index) })\n')
    elif line.strip() == ')':
        continue
    else:
        new_lines.append(line)

with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.writelines(new_lines)
