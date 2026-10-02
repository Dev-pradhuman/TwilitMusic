package com.twilitmusic.app.playback

import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.isActive
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter

class DesktopAudioPlayer : AudioPlayer {

    private val audioPlayerComponent = AudioPlayerComponent()
    private val mediaPlayer = audioPlayerComponent.mediaPlayer()

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

    private val _repeatMode = MutableStateFlow(0) // 0 = off, 1 = all, 2 = one
    override val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    private val _shuffleModeEnabled = MutableStateFlow(false)
    override val shuffleModeEnabled: StateFlow<Boolean> = _shuffleModeEnabled.asStateFlow()

    private var currentIndex = -1
    private var progressJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun playing(mediaPlayer: MediaPlayer) {
                _isPlaying.value = true
            }

            override fun paused(mediaPlayer: MediaPlayer) {
                _isPlaying.value = false
            }

            override fun finished(mediaPlayer: MediaPlayer) {
                _isPlaying.value = false
                playNextAuto()
            }

            override fun timeChanged(mediaPlayer: MediaPlayer, newTime: Long) {
                _currentPosition.value = newTime
            }

            override fun lengthChanged(mediaPlayer: MediaPlayer, newLength: Long) {
                _duration.value = newLength
            }
        })
    }

    override suspend fun init() {
        // vlcj is synchronous init usually
    }

    override fun playQueue(tracks: List<Track>, startIndex: Int) {
        if (tracks.isEmpty()) return
        _queue.value = tracks
        currentIndex = startIndex
        playCurrentIndex()
    }

    private fun playCurrentIndex() {
        if (currentIndex in 0 until _queue.value.size) {
            val track = _queue.value[currentIndex]
            _currentTrack.value = track
            // Play media
            mediaPlayer.media().play(track.sourceUrl)
        }
    }

    private fun playNextAuto() {
        // Handle repeat/shuffle later, simplistic approach:
        if (currentIndex < _queue.value.size - 1) {
            currentIndex++
            playCurrentIndex()
        }
    }

    override fun playPause() {
        if (mediaPlayer.status().isPlaying) {
            mediaPlayer.controls().pause()
        } else {
            mediaPlayer.controls().play()
        }
    }

    override fun skipToNext() {
        if (currentIndex < _queue.value.size - 1) {
            currentIndex++
            playCurrentIndex()
        }
    }

    override fun skipToPrevious() {
        if (currentIndex > 0) {
            currentIndex--
            playCurrentIndex()
        }
    }

    override fun seekTo(position: Long) {
        mediaPlayer.controls().setTime(position)
    }

    override fun removeTrack(index: Int) {
        val q = _queue.value.toMutableList()
        if (index in q.indices) {
            q.removeAt(index)
            _queue.value = q
            if (index < currentIndex) currentIndex--
            else if (index == currentIndex && q.isNotEmpty()) playCurrentIndex()
        }
    }

    override fun moveTrack(from: Int, to: Int) {
        val q = _queue.value.toMutableList()
        if (from in q.indices && to in q.indices) {
            val item = q.removeAt(from)
            q.add(to, item)
            _queue.value = q
            if (currentIndex == from) currentIndex = to
        }
    }

    override fun setRepeatMode(mode: Int) {
        _repeatMode.value = mode
    }

    override fun setShuffleModeEnabled(enabled: Boolean) {
        _shuffleModeEnabled.value = enabled
    }
}
