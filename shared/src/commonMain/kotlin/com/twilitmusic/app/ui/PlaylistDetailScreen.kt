package com.twilitmusic.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import com.twilitmusic.app.domain.model.Track

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistDetailScreen(
    playlistId: Long,
    viewModel: PlaylistDetailViewModel = koinViewModel(),
    onTrackClick: (Track) -> Unit
) {
    val playlist by viewModel.playlist.collectAsState()
    val tracks by viewModel.tracks.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(playlist?.name ?: "") },
                actions = {
                    IconButton(onClick = { /* play all */ }) {
                        Icon(Icons.Default.PlayArrow, "Play All")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(tracks.size) { index ->
                val trackEntity = tracks[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
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
                }
            }
        }
    }
}
