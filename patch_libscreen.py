import sys

with open('app/src/main/java/com/twilitmusic/app/ui/LibraryScreen.kt', 'r') as f:
    content = f.read()

old_sig = '''@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel(),
    onTrackClick: (Track) -> Unit
) {'''
new_sig = '''@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel(),
    onTrackClick: (Track) -> Unit,
    onPlaylistClick: (Long) -> Unit = {}
) {'''
content = content.replace(old_sig, new_sig)

old_row = '''                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),'''
new_row = '''                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onPlaylistClick(playlist.id) }
                                    .padding(16.dp),'''
content = content.replace(old_row, new_row)

with open('app/src/main/java/com/twilitmusic/app/ui/LibraryScreen.kt', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'r') as f:
    nav = f.read()

nav = nav.replace('LibraryScreen(onTrackClick = { viewModel.playTrack(it) })', 'LibraryScreen(onTrackClick = { viewModel.playTrack(it) }, onPlaylistClick = { navController.navigate(PlaylistDetailRoute(it)) })')
with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'w') as f:
    f.write(nav)
