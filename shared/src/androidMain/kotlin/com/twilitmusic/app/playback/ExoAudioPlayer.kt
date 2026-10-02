package com.twilitmusic.app.playback

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ExoAudioPlayer(
    private val context: Context
) : AudioPlayer {

    private var exoPlayer: ExoPlayer? = null
    
    private val _currentTrack = MutableStateFlow<Track?>(null)
    override val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()

    private val _queue = MutableStateFlow<List<Track>>(emptyList())
    override val queue: StateFlow<List<Track>> = _queue.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    override val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    override val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _repeatMode = MutableStateFlow(0)
    override val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    private val _shuffleModeEnabled = MutableStateFlow(false)
    override val shuffleModeEnabled: StateFlow<Boolean> = _shuffleModeEnabled.asStateFlow()

    private var progressJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override suspend fun init() {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _isPlaying.value = isPlaying
                        if (isPlaying) startProgressTracking() else stopProgressTracking()
                    }

                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        val track = _queue.value.find { it.id == mediaItem?.mediaId }
                        _currentTrack.value = track
                        _duration.value = duration.coerceAtLeast(0L)
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        if (playbackState == Player.STATE_READY) {
                            _duration.value = duration.coerceAtLeast(0L)
                        }
                    }

                    override fun onRepeatModeChanged(repeatMode: Int) {
                        _repeatMode.value = repeatMode
                    }

                    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                        _shuffleModeEnabled.value = shuffleModeEnabled
                    }
                })
            }
        }
    }

    override fun playQueue(tracks: List<Track>, startIndex: Int) {
        val player = exoPlayer ?: return
        _queue.value = tracks
        player.setMediaItems(tracks.map { MediaItem.Builder().setMediaId(it.id).setUri(it.sourceUrl).build() })
        player.seekTo(startIndex, 0L)
        player.prepare()
        player.play()
    }

    override fun playPause() {
        val player = exoPlayer ?: return
        if (player.isPlaying) player.pause() else player.play()
    }

    override fun skipToNext() {
        exoPlayer?.seekToNext()
    }

    override fun skipToPrevious() {
        exoPlayer?.seekToPrevious()
    }

    override fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
    }

    override fun removeTrack(index: Int) {
        val player = exoPlayer ?: return
        if (index in 0 until player.mediaItemCount) {
            player.removeMediaItem(index)
            val newList = _queue.value.toMutableList()
            if (index < newList.size) {
                newList.removeAt(index)
                _queue.value = newList
            }
        }
    }

    override fun moveTrack(from: Int, to: Int) {
        val player = exoPlayer ?: return
        if (from in 0 until player.mediaItemCount && to in 0 until player.mediaItemCount) {
            player.moveMediaItem(from, to)
            val newList = _queue.value.toMutableList()
            val item = newList.removeAt(from)
            newList.add(to, item)
            _queue.value = newList
        }
    }

    override fun setRepeatMode(mode: Int) {
        exoPlayer?.repeatMode = mode
    }

    override fun setShuffleModeEnabled(enabled: Boolean) {
        exoPlayer?.shuffleModeEnabled = enabled
    }

    private fun startProgressTracking() {
        progressJob?.cancel()
        progressJob = coroutineScope.launch {
            while (isActive) {
                _currentPosition.value = exoPlayer?.currentPosition ?: 0L
                delay(1000L)
            }
        }
    }

    private fun stopProgressTracking() {
        progressJob?.cancel()
    }
}
