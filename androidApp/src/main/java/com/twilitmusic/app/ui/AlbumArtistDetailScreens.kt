package com.twilitmusic.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twilitmusic.app.domain.model.Track

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    album: String,
    viewModel: MainViewModel,
    onTrackClick: (Track) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val albumTracks = (uiState.featuredTracks + uiState.newTracks).filter { it.title.contains(album, ignoreCase = true) } // Simplified grouping for Demo

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Album: $album", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(albumTracks) { track ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTrackClick(track) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(track.title, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(
    artist: String,
    viewModel: MainViewModel,
    onTrackClick: (Track) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val artistTracks = (uiState.featuredTracks + uiState.newTracks).filter { it.artist.contains(artist, ignoreCase = true) } // Simplified grouping for Demo

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Artist: $artist", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(artistTracks) { track ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTrackClick(track) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(track.title, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
