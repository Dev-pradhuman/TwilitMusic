package com.twilitmusic.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.media3.common.Player
import com.twilitmusic.app.R
import coil.compose.AsyncImage
import com.twilitmusic.app.domain.model.Track
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState
import androidx.compose.foundation.lazy.rememberLazyListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingScreen(
    track: Track,
    isPlaying: Boolean,
    onClose: () -> Unit,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    viewModel: MainViewModel
) {
    var showQueue by remember { mutableStateOf(false) }
    val position by viewModel.musicController.position.collectAsState()
    val duration by viewModel.musicController.duration.collectAsState()
    var sliderPosition by remember { mutableStateOf<Float?>(null) }
    val displayPosition = sliderPosition ?: position.toFloat()
    
    val shuffleModeEnabled by viewModel.musicController.shuffleModeEnabled.collectAsState()
    val repeatMode by viewModel.musicController.repeatMode.collectAsState()
    val isLiked by viewModel.isCurrentTrackLiked.collectAsState()

    if (showQueue) {
        QueueSheet(
            viewModel = viewModel,
            onClose = { showQueue = false }
        )
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.now_playing), style = MaterialTheme.typography.labelSmall) },
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(Icons.Default.KeyboardArrowDown, stringResource(R.string.close))
                        }
                    },
                    actions = {
                        IconButton(onClick = { showQueue = true }) {
                            Icon(Icons.Default.List, stringResource(R.string.queue))
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                AsyncImage(
                    model = track.artUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.large)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            track.title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            track.artist,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = { viewModel.toggleLike() }) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Slider(
                    value = if (duration > 0) displayPosition / duration.toFloat() else 0f,
                    onValueChange = { sliderPosition = it * duration.toFloat() },
                    onValueChangeFinished = {
                        sliderPosition?.let { viewModel.musicController.seekTo(it.toLong()) }
                        sliderPosition = null
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(formatTime(displayPosition.toLong()), style = MaterialTheme.typography.labelMedium)
                    Text(formatTime(duration), style = MaterialTheme.typography.labelMedium)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.musicController.toggleShuffle() }) {
                        Icon(
                            imageVector = Icons.Default.Shuffle,
                            contentDescription = "Shuffle",
                            tint = if (shuffleModeEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = onPrev) {
                        Icon(Icons.Default.SkipPrevious, stringResource(R.string.previous), modifier = Modifier.size(48.dp))
                    }
                    FloatingActionButton(onClick = onPlayPause) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) stringResource(R.string.pause) else stringResource(R.string.play)
                        )
                    }
                    IconButton(onClick = onNext) {
                        Icon(Icons.Default.SkipNext, stringResource(R.string.next), modifier = Modifier.size(48.dp))
                    }
                    IconButton(onClick = { viewModel.musicController.cycleRepeatMode() }) {
                        val icon = if (repeatMode == Player.REPEAT_MODE_ONE) Icons.Default.RepeatOne else Icons.Default.Repeat
                        val tint = if (repeatMode != Player.REPEAT_MODE_OFF) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        Icon(
                            imageVector = icon,
                            contentDescription = "Repeat",
                            tint = tint
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueSheet(
    viewModel: MainViewModel,
    onClose: () -> Unit
) {
    val queueFlow by viewModel.musicController.queue.collectAsState()
    val currentTrack by viewModel.musicController.currentTrack.collectAsState()

    var localQueue by remember { mutableStateOf(queueFlow) }

    LaunchedEffect(queueFlow) {
        localQueue = queueFlow
    }

    var dragStartIndex by remember { mutableStateOf(-1) }
    var dragEndIndex by remember { mutableStateOf(-1) }

    val lazyListState = rememberLazyListState()
    val state = rememberReorderableLazyListState(lazyListState) { from, to ->
        localQueue = localQueue.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
        if (dragStartIndex == -1) dragStartIndex = from.index
        dragEndIndex = to.index
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.queue)) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.KeyboardArrowDown, stringResource(R.string.close))
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            state = lazyListState,
            contentPadding = padding
        ) {
            items(localQueue.size, { it }) { index ->
                val track = localQueue[index]
                ReorderableItem(state, key = index) { isDragging ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.playQueue(localQueue, index) }
                            .background(
                                if (isDragging) MaterialTheme.colorScheme.surfaceVariant
                                else if (track.id == currentTrack?.id) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surface
                            )
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DragHandle,
                            contentDescription = stringResource(R.string.drag),
                            modifier = Modifier.longPressDraggableHandle(
                                onDragStopped = {
                                    if (dragStartIndex != -1 && dragEndIndex != -1 && dragStartIndex != dragEndIndex) {
                                        viewModel.moveTrack(dragStartIndex, dragEndIndex)
                                    }
                                    dragStartIndex = -1
                                    dragEndIndex = -1
                                }
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        AsyncImage(
                            model = track.artUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(track.title)
                            Text(track.artist, style = MaterialTheme.typography.labelSmall)
                        }
                        IconButton(onClick = { viewModel.removeTrack(index) }) {
                            Icon(Icons.Default.Close, stringResource(R.string.remove))
                        }
                    }
                }
            }
        }
    }
}
