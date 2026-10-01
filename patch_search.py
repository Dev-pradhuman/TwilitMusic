import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

old_search = '''@Composable
fun SearchScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(R.string.search_placeholder))
    }
}'''
content = content.replace(old_search, '')

old_switch = '''                when (currentTab) {
                    0 -> HomeTab(uiState, onTrackClick = { viewModel.playTrack(it) })
                    1 -> SearchScreenPlaceholder()
                    2 -> LibraryScreenPlaceholder()
                }'''
new_switch = '''                when (currentTab) {
                    0 -> HomeTab(uiState, onTrackClick = { viewModel.playTrack(it) })
                    1 -> SearchScreen(viewModel, onTrackClick = { viewModel.playTrack(it) })
                    2 -> LibraryScreenPlaceholder()
                }'''
content = content.replace(old_switch, new_switch)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
