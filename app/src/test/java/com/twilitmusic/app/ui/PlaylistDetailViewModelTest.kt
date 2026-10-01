package com.twilitmusic.app.ui

import androidx.lifecycle.SavedStateHandle
import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistDetailViewModelTest {
    private lateinit var playlistDao: PlaylistDao
    private lateinit var viewModel: PlaylistDetailViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        playlistDao = mock()
        
        val savedStateHandle = SavedStateHandle(mapOf("playlistId" to 1L))
        whenever(playlistDao.getAllPlaylists()).thenReturn(flowOf(listOf(PlaylistEntity(id = 1L, name = "My Playlist"))))
        whenever(playlistDao.getTracksForPlaylist(1L)).thenReturn(flowOf(listOf(PlaylistTrackEntity(1L, 1L, "T1", "A1", "U1", "S1", "Title", 0))))
        
        viewModel = PlaylistDetailViewModel(savedStateHandle, playlistDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `playlist loads correctly`() = runTest(testDispatcher) {
        val job = launch { viewModel.playlist.collect {} }
        advanceUntilIdle()
        assertEquals("My Playlist", viewModel.playlist.value?.name)
        job.cancel()
    }

    @Test
    fun `tracks load correctly`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        assertEquals(1, viewModel.tracks.value.size)
        assertEquals("T1", viewModel.tracks.value[0].trackId)
        job.cancel()
    }

    @Test
    fun `rename playlist calls dao`() = runTest(testDispatcher) {
        val job = launch { viewModel.playlist.collect {} }
        advanceUntilIdle()
        viewModel.renamePlaylist("New Name")
        advanceUntilIdle()
        verify(playlistDao).createPlaylist(any())
        job.cancel()
    }

    @Test
    fun `remove track calls dao`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        viewModel.removeTrack(1L)
        advanceUntilIdle()
        verify(playlistDao).removeTrackFromPlaylist(1L)
        job.cancel()
    }

    @Test
    fun `move track updates order`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        viewModel.moveTrack(0, 0)
        advanceUntilIdle()
        verify(playlistDao).addTrackToPlaylist(any())
        job.cancel()
    }
}
