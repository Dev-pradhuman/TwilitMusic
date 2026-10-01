package com.twilitmusic.app.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.twilitmusic.app.domain.model.Track
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlaylistDetailScreen(
    viewModel: PlaylistDetailViewModel = hiltViewModel(),
    onTrackClick: (Track) -> Unit
) {
    val playlist by viewModel.playlist.collectAsState()
    val tracks by viewModel.tracks.collectAsState()

    val lazyListState = rememberLazyListState()
    val reorderableState = rememberReorderableLazyListState(lazyListState) { from, to ->
        viewModel.moveTrack(from.index, to.index)
    }

    var showRenameDialog by remember { mutableStateOf(false) }

    if (showRenameDialog) {
        var newName by remember { mutableStateOf(playlist?.name ?: "") }
        AlertDialog(
            onDismissRequest = { showRenameDialog = false },
            title = { Text("Rename Playlist") },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Name") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newName.isNotBlank()) {
                        viewModel.renamePlaylist(newName)
                    }
                    showRenameDialog = false
                }) { Text("Save") }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(playlist?.name ?: "", style = MaterialTheme.typography.headlineMedium)
            IconButton(onClick = { showRenameDialog = true }) {
                Icon(Icons.Default.Edit, "Rename")
            }
        }
        
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { if (tracks.isNotEmpty()) onTrackClick(Track(tracks[0].trackId, tracks[0].title, tracks[0].artist, tracks[0].artUrl, tracks[0].sourceUrl)) }) {
                Icon(Icons.Default.PlayArrow, "Play")
                Spacer(Modifier.width(8.dp))
                Text("Play")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            itemsIndexed(tracks, key = { _, item -> item.id }) { index, trackEntity ->
                ReorderableItem(reorderableState, key = trackEntity.id) { isDragging ->
                    val elevation = if (isDragging) 8.dp else 0.dp
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (isDragging) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent)
                            .clickable {
                                onTrackClick(Track(trackEntity.trackId, trackEntity.title, trackEntity.artist, trackEntity.artUrl, trackEntity.sourceUrl))
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(trackEntity.title, style = MaterialTheme.typography.bodyLarge)
                            Text(trackEntity.artist, style = MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { viewModel.removeTrack(trackEntity.id) }) {
                            Icon(Icons.Default.Close, "Remove")
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier.draggableHandle()
                        ) {
                            Icon(Icons.Default.DragHandle, "Reorder")
                        }
                    }
                }
            }
        }
    }
}
