package com.twilitmusic.app.ui

import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.MusicSource
import com.twilitmusic.app.playback.MusicController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit val mockMusicSource: MusicSource
    private lateinit val mockMusicController: MusicController
    private lateinit val viewModel: MainViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockMusicSource = mock(MusicSource::class.java)
        mockMusicController = mock(MusicController::class.java)
        viewModel = MainViewModel(mockMusicSource, mockMusicController)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadHomeData_updatesUiState() = runTest(testDispatcher) {
        val tracks = listOf(Track("1", "A", "B", "C", "D"))
        `when`(mockMusicSource.getFeaturedTracks()).thenReturn(tracks)
        `when`(mockMusicSource.getNewTracks()).thenReturn(tracks)

        // Initialize view model
        viewModel = MainViewModel(mockMusicSource, mockMusicController)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(false, state.isLoading)
        assertEquals(tracks, state.featuredTracks)
        assertEquals(tracks, state.newTracks)
    }

    @Test
    fun playPause_callsController() = runTest(testDispatcher) {
        viewModel.playPause()
        verify(mockMusicController).playPause()
    }

    @Test
    fun removeTrack_callsController() = runTest(testDispatcher) {
        viewModel.removeTrack(1)
        verify(mockMusicController).removeTrack(1)
    }

    @Test
    fun moveTrack_callsController() = runTest(testDispatcher) {
        viewModel.moveTrack(0, 2)
        verify(mockMusicController).moveTrack(0, 2)
    }
}
