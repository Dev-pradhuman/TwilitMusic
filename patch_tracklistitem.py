import sys

with open('app/src/main/java/com/twilitmusic/app/ui/HomeScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.material3.*', 'import androidx.compose.material3.*\nimport androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.MoreVert')

old_track_item = '''@Composable
fun TrackListItem(track: Track, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(track.title, style = MaterialTheme.typography.bodyLarge)
            Text(track.artist, style = MaterialTheme.typography.bodyMedium)
        }
    }
}'''

new_track_item = '''@Composable
fun TrackListItem(track: Track, onClick: () -> Unit, onDownload: () -> Unit = {}, onAddToPlaylist: () -> Unit = {}) {
    var showMenu by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(track.title, style = MaterialTheme.typography.bodyLarge)
            Text(track.artist, style = MaterialTheme.typography.bodyMedium)
        }
        Box {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, "More")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(text = { Text("Download") }, onClick = { showMenu = false; onDownload() })
                DropdownMenuItem(text = { Text("Add to Playlist") }, onClick = { showMenu = false; onAddToPlaylist() })
            }
        }
    }
}'''

content = content.replace(old_track_item, new_track_item)

# Update HomeScreen to pass functions
content = content.replace('TrackListItem(track = track) { viewModel.playTrack(track) }', 'TrackListItem(track = track, onClick = { viewModel.playTrack(track) }, onDownload = { viewModel.downloadTrack(track) })')

with open('app/src/main/java/com/twilitmusic/app/ui/HomeScreen.kt', 'w') as f:
    f.write(content)
