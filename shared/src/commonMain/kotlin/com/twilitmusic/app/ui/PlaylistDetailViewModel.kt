package com.twilitmusic.app.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaylistDetailViewModel (
    private val savedStateHandle: SavedStateHandle,
    private val playlistDao: PlaylistDao
) : ViewModel() {

    private val playlistId: Long = checkNotNull(savedStateHandle["playlistId"])

    val playlist: StateFlow<PlaylistEntity?> = playlistDao.getAllPlaylists().map { list ->
        list.find { it.id == playlistId }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val tracks: StateFlow<List<PlaylistTrackEntity>> = playlistDao.getTracksForPlaylist(playlistId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun renamePlaylist(newName: String) {
        val current = playlist.value ?: return
        viewModelScope.launch {
            playlistDao.createPlaylist(current.copy(name = newName)) // REPLACE conflict strategy will update it
        }
    }

    fun removeTrack(playlistTrackId: Long) {
        viewModelScope.launch {
            playlistDao.removeTrackFromPlaylist(playlistTrackId)
        }
    }

    fun moveTrack(fromIndex: Int, toIndex: Int) {
        val currentList = tracks.value.toMutableList()
        val item = currentList.removeAt(fromIndex)
        currentList.add(toIndex, item)
        viewModelScope.launch {
            currentList.forEachIndexed { idx, t ->
                playlistDao.addTrackToPlaylist(t.copy(position = idx))
            }
        }
    }
}
