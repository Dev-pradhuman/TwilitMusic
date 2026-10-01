package com.twilitmusic.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.twilitmusic.app.R
import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    onTrackClick: (Track) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }
    
    val allTracks = viewModel.uiState.collectAsState().value.featuredTracks + viewModel.uiState.collectAsState().value.newTracks
    val searchResults = if (debouncedQuery.isBlank()) {
        emptyList()
    } else {
        allTracks.filter { it.title.contains(debouncedQuery, ignoreCase = true) || it.artist.contains(debouncedQuery, ignoreCase = true) }.distinctBy { it.id }
    }

    LaunchedEffect(query) {
        delay(300)
        debouncedQuery = query
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_placeholder)) },
            singleLine = true
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(searchResults) { track ->
                TrackItem(track = track, onClick = { onTrackClick(track) })
            }
        }
    }
}
