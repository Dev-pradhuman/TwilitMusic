package com.twilitmusic.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.foundation.layout.Box
import com.twilitmusic.app.R
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.twilitmusic.app.domain.model.Track

@Composable
fun TwilitAppScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsState()
    val isPlaying by viewModel.musicController.isPlaying.collectAsState()
    val position by viewModel.musicController.position.collectAsState()
    val duration by viewModel.musicController.duration.collectAsState()
    val currentTrack by viewModel.musicController.currentTrack.collectAsState()
    
    var showNowPlaying by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            Column {
                if (currentTrack != null) {
                    MiniPlayer(
                        track = currentTrack!!,
                        isPlaying = isPlaying,
                        progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                        onPlayPause = viewModel::playPause,
                        onClick = { showNowPlaying = true }
                    )
                }
                NavigationBar {
                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0; navController.navigate(HomeRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
                        label = { Text(stringResource(R.string.home)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1; navController.navigate(SearchRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search)) },
                        label = { Text(stringResource(R.string.search)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2; navController.navigate(LibraryRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.LibraryMusic, contentDescription = stringResource(R.string.library)) },
                        label = { Text(stringResource(R.string.library)) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(navController = navController, viewModel = viewModel)
        }
    }
    
    if (showNowPlaying && currentTrack != null) {
        NowPlayingScreen(
            track = currentTrack!!,
            isPlaying = isPlaying,
            onClose = { showNowPlaying = false },
            onPlayPause = viewModel::playPause,
            onNext = viewModel::skipToNext,
            onPrev = viewModel::skipToPrevious,
            viewModel = viewModel
        )
    }
}

@Composable
fun HomeScreen(
    uiState: MainUiState,
    onPlayTrack: (Track) -> Unit
) {
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            Text(
                stringResource(R.string.featured),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(uiState.featuredTracks) { track ->
            TrackItem(track = track, onClick = { onPlayTrack(track) })
        }
        item {
            Text(
                stringResource(R.string.new_tracks),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(uiState.newTracks) { track ->
            TrackItem(track = track, onClick = { onPlayTrack(track) })
        }
    }
}



@Composable
fun MiniPlayer(
    track: Track,
    isPlaying: Boolean,
    progress: Float,
    onPlayPause: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = track.artUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(track.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(track.artist, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.labelSmall)
            }
            IconButton(onClick = onPlayPause) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) stringResource(R.string.pause) else stringResource(R.string.play)
                )
            }
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            trackColor = androidx.compose.ui.graphics.Color.Transparent
        )
    }
}
}




