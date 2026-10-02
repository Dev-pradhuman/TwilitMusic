package com.twilitmusic.app.playback
import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class IosAudioPlayer : AudioPlayer {
    override val currentTrack = MutableStateFlow<Track?>(null).asStateFlow()
    override val queue = MutableStateFlow<List<Track>>(emptyList()).asStateFlow()
    override val isPlaying = MutableStateFlow(false).asStateFlow()
    override val currentPosition = MutableStateFlow(0L).asStateFlow()
    override val duration = MutableStateFlow(0L).asStateFlow()
    override val repeatMode = MutableStateFlow(0).asStateFlow()
    override val shuffleModeEnabled = MutableStateFlow(false).asStateFlow()

    override suspend fun init() {}
    override fun playQueue(tracks: List<Track>, startIndex: Int) {}
    override fun playPause() {}
    override fun skipToNext() {}
    override fun skipToPrevious() {}
    override fun seekTo(position: Long) {}
    override fun removeTrack(index: Int) {}
    override fun moveTrack(from: Int, to: Int) {}
    override fun setRepeatMode(mode: Int) {}
    override fun setShuffleModeEnabled(enabled: Boolean) {}
}
