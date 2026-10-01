package com.twilitmusic.app.ui

import androidx.lifecycle.ViewModel
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.offline.DownloadService
import com.twilitmusic.app.playback.TwilitDownloadService
import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.offline.DownloadManager
import android.app.Application
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController,
    @ApplicationContext private val context: Context,
    private val downloadManager: DownloadManager,
    private val libraryRepository: LibraryRepository,
    private val queueDao: QueueDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    
    @OptIn(ExperimentalCoroutinesApi::class)
    val isCurrentTrackLiked = musicController.currentTrack.flatMapLatest { track ->
        if (track == null) flowOf(false) else libraryRepository.isLiked(track.id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    
    fun toggleLike() {
        val track = musicController.currentTrack.value ?: return
        viewModelScope.launch {
            libraryRepository.toggleLike(track, !isCurrentTrackLiked.value)
        }
    }


    val isOffline = MutableStateFlow(false)

    init {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { isOffline.value = false }
            override fun onLost(network: Network) { isOffline.value = true }
        }
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        
        viewModelScope.launch {
            musicController.init()
            loadHomeData()
        }
    }

    private suspend fun loadHomeData() {
        _uiState.update { it.copy(isLoading = true) }
        val featured = musicSource.getFeaturedTracks().getOrDefault(emptyList())
        val new = musicSource.getNewTracks().getOrDefault(emptyList())
        _uiState.update { 
            it.copy(
                featuredTracks = featured,
                newTracks = new,
                isLoading = false
            )
        }
    }

    
    fun downloadTrack(track: Track) {
        if (track.sourceUrl.isEmpty()) return
        val downloadRequest = DownloadRequest.Builder(track.id, Uri.parse(track.sourceUrl)).build()
        DownloadService.sendAddDownload(context, TwilitDownloadService::class.java, downloadRequest, false)
    }

    val downloadedTrackIds = MutableStateFlow<Set<String>>(emptySet())
    
    // In a real app we would observe downloadManager.addListener, but for now we poll or rely on UI updates

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
