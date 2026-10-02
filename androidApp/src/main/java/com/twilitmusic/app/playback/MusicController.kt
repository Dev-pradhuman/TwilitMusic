package com.twilitmusic.app.playback

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.data.local.entity.QueueTrackEntity
import com.twilitmusic.app.data.local.entity.PlaybackStateEntity
import com.twilitmusic.app.playback.AudioPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class MusicController (
    private val context: Context,
    private val libraryRepository: LibraryRepository,
    private val queueDao: QueueDao
) : AudioPlayer {
    internal var mediaController: MediaController? = null
    
    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    
    private val _currentTrack = MutableStateFlow<Track?>(null)
    override val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()
    
    private val _queue = MutableStateFlow<List<Track>>(emptyList())
    override val queue: StateFlow<List<Track>> = _queue.asStateFlow()

    private val _position = MutableStateFlow(0L)
    override val currentPosition: StateFlow<Long> = _position.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    override val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _bufferedPosition = MutableStateFlow(0L)
    val bufferedPosition: StateFlow<Long> = _bufferedPosition.asStateFlow()

    private val _shuffleModeEnabled = MutableStateFlow(false)
    override val shuffleModeEnabled: StateFlow<Boolean> = _shuffleModeEnabled.asStateFlow()

    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_OFF)
    override val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    override suspend fun init() {
        if (mediaController != null) return
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        mediaController = MediaController.Builder(context, sessionToken).buildAsync().await()
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                if (_isPlaying.value) {
                    mediaController?.let {
                        _position.value = it.currentPosition
                        _duration.value = it.duration.coerceAtLeast(0L)
                        _bufferedPosition.value = it.bufferedPosition
                    }
                }
                delay(500)
            }
        }
        mediaController?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentTrack(mediaItem)
                mediaItem?.let {
                    CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                        _queue.value.find { track -> track.id == it.mediaId }?.let { track ->
                            libraryRepository.addPlayHistory(track)
                        }
                    }
                }
            }
            override fun onTimelineChanged(timeline: androidx.media3.common.Timeline, reason: Int) {
                updateQueue()
            }
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                _shuffleModeEnabled.value = shuffleModeEnabled
                updateQueue()
            }
            override fun onRepeatModeChanged(repeatMode: Int) {
                _repeatMode.value = repeatMode
            }
        })
    }
    
    private fun updateCurrentTrack(mediaItem: MediaItem?) {
        if (mediaItem == null) {
            _currentTrack.value = null
            return
        }
        _currentTrack.value = _queue.value.find { it.id == mediaItem.mediaId }
    }
    
    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    private fun updateQueue() {
        val controller = mediaController ?: return
        val newQueue = mutableListOf<Track>()
        for (i in 0 until controller.mediaItemCount) {
            val item = controller.getMediaItemAt(i)
            // Extract track from mediaItem.metadata or match by ID if we keep a map
            // For simplicity we use the existing track in _queue or recreate it if possible
            val existing = _queue.value.find { it.id == item.mediaId }
            if (existing != null) {
                newQueue.add(existing)
            } else {
                newQueue.add(Track(
                    id = item.mediaId,
                    title = item.mediaMetadata.title.toString(),
                    artist = item.mediaMetadata.artist.toString(),
                    artUrl = item.mediaMetadata.artworkUri?.toString() ?: "",
                    sourceUrl = item.localConfiguration?.uri?.toString() ?: ""
                ))
            }
        }
        _queue.value = newQueue
        updateCurrentTrack(controller.currentMediaItem)
        saveQueueState()
    }
    
    private fun saveQueueState() {
        val tracks = _queue.value.mapIndexed { index, track -> 
            QueueTrackEntity(trackId = track.id, title = track.title, artist = track.artist, artUrl = track.artUrl, sourceUrl = track.sourceUrl, position = index)
        }
        val currentIndex = mediaController?.currentMediaItemIndex ?: 0
        val pos = mediaController?.currentPosition ?: 0L
        CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            queueDao.saveFullState(tracks, PlaybackStateEntity(id = 1, currentIndex = currentIndex, positionMs = pos))
        }
    }

    fun playTrack(track: Track) {
        val controller = mediaController ?: return
        val mediaItem = MediaItem.Builder()
            .setMediaId(track.id)
            .setUri(track.sourceUrl)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(track.title)
                    .setArtist(track.artist)
                    .setArtworkUri(android.net.Uri.parse(track.artUrl))
                    .build()
            )
            .build()
        controller.setMediaItem(mediaItem)
        controller.prepare()
        controller.play()
    }
    
    override fun playQueue(tracks: List<Track>, startIndex: Int) {
        val controller = mediaController ?: return
        val items = tracks.map { track ->
            MediaItem.Builder()
                .setMediaId(track.id)
                .setUri(track.sourceUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(track.title)
                        .setArtist(track.artist)
                        .setArtworkUri(android.net.Uri.parse(track.artUrl))
                        .build()
                )
                .build()
        }
        controller.setMediaItems(items, startIndex, 0L)
        controller.prepare()
        controller.play()
    }

    fun setQueueWithoutPlaying(tracks: List<Track>, startIndex: Int = 0) {
        val controller = mediaController ?: return
        val items = tracks.map { track ->
            MediaItem.Builder()
                .setMediaId(track.id)
                .setUri(track.sourceUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(track.title)
                        .setArtist(track.artist)
                        .setArtworkUri(android.net.Uri.parse(track.artUrl))
                        .build()
                )
                .build()
        }
        controller.setMediaItems(items, startIndex, 0L)
        controller.prepare()
        // Do not play automatically
    }
    
    override fun playPause() {
        val controller = mediaController ?: return
        if (controller.isPlaying) {
            controller.pause()
        } else {
            controller.play()
        }
    }
    
    override fun skipToNext() {
        mediaController?.seekToNext()
    }
    
    override fun skipToPrevious() {
        mediaController?.seekToPrevious()
    }
    
    override fun removeTrack(index: Int) {
        mediaController?.removeMediaItem(index)
    }
    
    override fun moveTrack(from: Int, to: Int) {
        mediaController?.moveMediaItem(from, to)
    }

    override fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
    }

    fun toggleShuffle() {
        val controller = mediaController ?: return
        controller.shuffleModeEnabled = !controller.shuffleModeEnabled
    }

    override fun setRepeatMode(mode: Int) { mediaController?.repeatMode = mode }
    override fun setShuffleModeEnabled(enabled: Boolean) { mediaController?.shuffleModeEnabled = enabled }

    fun cycleRepeatMode() {
        val controller = mediaController ?: return
        controller.repeatMode = when (controller.repeatMode) {
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_OFF
        }
    }
}
