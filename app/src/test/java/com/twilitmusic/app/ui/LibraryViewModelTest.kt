package com.twilitmusic.app.ui

import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LibraryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var libraryRepository: LibraryRepository
    private lateinit var playlistDao: PlaylistDao
    private lateinit var viewModel: LibraryViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        libraryRepository = mock()
        playlistDao = mock()
        
        whenever(libraryRepository.getLikedTracks()).thenReturn(flowOf(emptyList()))
        whenever(libraryRepository.getPlayHistory()).thenReturn(flowOf(emptyList()))
        whenever(playlistDao.getAllPlaylists()).thenReturn(flowOf(emptyList()))
        
        viewModel = LibraryViewModel(libraryRepository, playlistDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `createPlaylist calls dao`() = runTest {
        viewModel.createPlaylist("My Playlist")
        testDispatcher.scheduler.advanceUntilIdle()
        verify(playlistDao).createPlaylist(org.mockito.kotlin.any())
    }
    
    @Test
    fun `deletePlaylist calls dao`() = runTest {
        viewModel.deletePlaylist(1L)
        testDispatcher.scheduler.advanceUntilIdle()
        verify(playlistDao).deletePlaylist(1L)
    }
}
