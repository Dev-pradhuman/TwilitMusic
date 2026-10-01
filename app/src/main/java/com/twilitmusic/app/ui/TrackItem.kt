package com.twilitmusic.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twilitmusic.app.domain.model.Track

@Composable
fun TrackItem(
    track: Track,
    isDownloaded: Boolean = false,
    onClick: () -> Unit,
    onDownload: () -> Unit = {},
    onAddToPlaylist: () -> Unit = {}
) {
    var showMenu by remember { mutableStateOf(false) }
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
        if (isDownloaded) {
            Icon(Icons.Default.DownloadDone, "Downloaded", tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(8.dp))
        }
        Box {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, "More")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                if (!isDownloaded) {
                    DropdownMenuItem(text = { Text("Download") }, onClick = { showMenu = false; onDownload() })
                }
                DropdownMenuItem(text = { Text("Add to Playlist") }, onClick = { showMenu = false; onAddToPlaylist() })
            }
        }
    }
}
