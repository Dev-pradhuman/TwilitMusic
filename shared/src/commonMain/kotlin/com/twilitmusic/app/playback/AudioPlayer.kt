package com.twilitmusic.app.playback

import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.flow.StateFlow

interface AudioPlayer {
    val currentTrack: StateFlow<Track?>
    val queue: StateFlow<List<Track>>
    val isPlaying: StateFlow<Boolean>
    val currentPosition: StateFlow<Long>
    val duration: StateFlow<Long>
    val repeatMode: StateFlow<Int>
    val shuffleModeEnabled: StateFlow<Boolean>

    suspend fun init()
    fun playQueue(tracks: List<Track>, startIndex: Int = 0)
    fun playPause()
    fun skipToNext()
    fun skipToPrevious()
    fun seekTo(position: Long)
    fun removeTrack(index: Int)
    fun moveTrack(from: Int, to: Int)
    fun setRepeatMode(mode: Int)
    fun setShuffleModeEnabled(enabled: Boolean)
}
