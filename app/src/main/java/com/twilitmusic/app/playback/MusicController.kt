package com.twilitmusic.app.playback

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.twilitmusic.app.domain.model.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.guava.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicController @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var mediaController: MediaController? = null
    
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    
    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()
    
    private val _queue = MutableStateFlow<List<Track>>(emptyList())
    val queue: StateFlow<List<Track>> = _queue.asStateFlow()

    suspend fun init() {
        if (mediaController != null) return
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        mediaController = MediaController.Builder(context, sessionToken).buildAsync().await()
        mediaController?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentTrack(mediaItem)
            }
            override fun onTimelineChanged(timeline: androidx.media3.common.Timeline, reason: Int) {
                updateQueue()
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
    
    fun playQueue(tracks: List<Track>, startIndex: Int = 0) {
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
    
    fun playPause() {
        val controller = mediaController ?: return
        if (controller.isPlaying) {
            controller.pause()
        } else {
            controller.play()
        }
    }
    
    fun skipToNext() {
        mediaController?.seekToNext()
    }
    
    fun skipToPrevious() {
        mediaController?.seekToPrevious()
    }
    
    fun removeTrack(index: Int) {
        mediaController?.removeMediaItem(index)
    }
    
    fun moveTrack(fromIndex: Int, toIndex: Int) {
        mediaController?.moveMediaItem(fromIndex, toIndex)
    }
}
