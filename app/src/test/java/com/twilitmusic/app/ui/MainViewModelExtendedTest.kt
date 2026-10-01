package com.twilitmusic.app.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.domain.repository.MusicSource
import com.twilitmusic.app.playback.MusicController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
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
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelExtendedTest {

    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var application: Application
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    private lateinit var musicSource: MusicSource
    private lateinit var musicController: MusicController
    private lateinit var libraryRepository: LibraryRepository
    private lateinit var queueDao: QueueDao
    private lateinit var viewModel: MainViewModel

    private val mockTrack = Track("1", "Test", "Artist", "url", "url")
    private val currentTrackFlow = MutableStateFlow<Track?>(mockTrack)

    @Before
    fun setup() = kotlinx.coroutines.test.runTest {
        Dispatchers.setMain(testDispatcher)
        application = mock()
        prefs = mock()
        prefsEditor = mock()
        musicSource = mock()
        musicController = mock()
        libraryRepository = mock()
        queueDao = mock()
        
        whenever(application.getSharedPreferences(any(), any())).thenReturn(prefs)
        whenever(prefs.edit()).thenReturn(prefsEditor)
        whenever(prefs.getString(any(), any())).thenReturn("")
        whenever(prefs.getInt(any(), any())).thenReturn(0)
        
        whenever(musicController.currentTrack).thenReturn(currentTrackFlow)
        whenever(musicController.queue).thenReturn(MutableStateFlow(emptyList()))
        whenever(musicController.isPlaying).thenReturn(MutableStateFlow(false))
        whenever(musicController.position).thenReturn(MutableStateFlow(0L))
        whenever(musicController.duration).thenReturn(MutableStateFlow(0L))
        whenever(musicController.bufferedPosition).thenReturn(MutableStateFlow(0L))
        whenever(musicController.shuffleModeEnabled).thenReturn(MutableStateFlow(false))
        whenever(musicController.repeatMode).thenReturn(MutableStateFlow(0))
        
        whenever(libraryRepository.isLiked(any())).thenReturn(MutableStateFlow(false))
        whenever(queueDao.getQueue()).thenReturn(emptyList())
        whenever(queueDao.getPlaybackState()).thenReturn(null)
        whenever(musicSource.getFeaturedTracks()).thenReturn(emptyList())
        whenever(musicSource.getNewTracks()).thenReturn(emptyList())
        
        viewModel = MainViewModel(application, musicSource, musicController, libraryRepository, queueDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `toggleLike calls repository`() = runTest {
        viewModel.toggleLike()
        testDispatcher.scheduler.advanceUntilIdle()
        verify(libraryRepository).toggleLike(mockTrack, true)
    }
}
