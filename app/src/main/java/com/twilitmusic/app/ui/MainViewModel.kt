package com.twilitmusic.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.MusicSource
import com.twilitmusic.app.playback.MusicController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val featuredTracks: List<Track> = emptyList(),
    val newTracks: List<Track> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val musicSource: MusicSource,
    val musicController: MusicController
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            musicController.init()
            loadHomeData()
        }
    }

    private suspend fun loadHomeData() {
        _uiState.update { it.copy(isLoading = true) }
        val featured = musicSource.getFeaturedTracks()
        val new = musicSource.getNewTracks()
        _uiState.update { 
            it.copy(
                featuredTracks = featured,
                newTracks = new,
                isLoading = false
            )
        }
    }

    fun playTrack(track: Track) {
        musicController.playQueue(listOf(track))
    }
    
    fun playQueue(tracks: List<Track>, startIndex: Int) {
        musicController.playQueue(tracks, startIndex)
    }

    fun playPause() = musicController.playPause()
    fun skipToNext() = musicController.skipToNext()
    fun skipToPrevious() = musicController.skipToPrevious()
    fun removeTrack(index: Int) = musicController.removeTrack(index)
    fun moveTrack(from: Int, to: Int) = musicController.moveTrack(from, to)
}
