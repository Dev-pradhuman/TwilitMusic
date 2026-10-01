import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

old_ph = '''@Composable
fun LibraryScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(R.string.library_placeholder))
    }
}'''
content = content.replace(old_ph, '')

old_switch = '''                    2 -> LibraryScreenPlaceholder()'''
new_switch = '''                    2 -> LibraryScreen(onTrackClick = { viewModel.playTrack(it) })'''
content = content.replace(old_switch, new_switch)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
