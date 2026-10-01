package com.twilitmusic.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.data.local.dao.PlaylistDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository,
    private val playlistDao: PlaylistDao
) : ViewModel() {

    val likedTracks = libraryRepository.getLikedTracks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val playHistory = libraryRepository.getPlayHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        
    val playlists = playlistDao.getAllPlaylists()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            playlistDao.createPlaylist(PlaylistEntity(name = name))
        }
    }
    
    fun deletePlaylist(id: Long) {
        viewModelScope.launch {
            playlistDao.deletePlaylist(id)
        }
    }
}
