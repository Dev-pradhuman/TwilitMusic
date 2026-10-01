import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

old_switch = '''            when (currentTab) {
                0 -> HomeScreen(uiState, onPlayTrack = viewModel::playTrack)
                1 -> SearchScreenPlaceholder()
                2 -> LibraryScreenPlaceholder()
            }'''
new_switch = '''            when (currentTab) {
                0 -> HomeScreen(uiState, onPlayTrack = viewModel::playTrack)
                1 -> SearchScreen(viewModel, onTrackClick = { viewModel.playTrack(it) })
                2 -> LibraryScreen(onTrackClick = { viewModel.playTrack(it) })
            }'''
content = content.replace(old_switch, new_switch)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
