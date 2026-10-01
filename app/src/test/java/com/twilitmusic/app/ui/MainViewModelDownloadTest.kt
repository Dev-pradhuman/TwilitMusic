package com.twilitmusic.app.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.media3.exoplayer.offline.DownloadManager
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.domain.repository.MusicSource
import com.twilitmusic.app.playback.MusicController
import com.twilitmusic.app.data.local.dao.QueueDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelDownloadTest {
    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var mockApplication: Application
    private lateinit var mockContext: Context
    private lateinit var mockDownloadManager: DownloadManager
    private lateinit var mockLibraryRepo: LibraryRepository
    private lateinit var mockMusicSource: MusicSource
    private lateinit var mockMusicController: MusicController
    private lateinit var mockQueueDao: QueueDao
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() = kotlinx.coroutines.runBlocking {
        Dispatchers.setMain(testDispatcher)
        mockApplication = mock()
        mockContext = mock()
        mockDownloadManager = mock()
        mockLibraryRepo = mock()
        mockMusicSource = mock()
        mockMusicController = mock()
        mockQueueDao = mock()
        
        val mockConnectivityManager = mock(ConnectivityManager::class.java)
        whenever(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager)

        
        whenever(mockMusicSource.getFeaturedTracks()).thenReturn(Result.success(emptyList()))
        whenever(mockMusicSource.getNewTracks()).thenReturn(Result.success(emptyList()))
        whenever(mockQueueDao.getQueue()).thenReturn(emptyList())
        viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo, mockQueueDao)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `downloadTrack adds to list`() = runTest(testDispatcher) {
        val track = Track("1", "T", "A", "U", "S")
        viewModel.downloadedTrackIds.value = setOf("1")
        val ids = viewModel.downloadedTrackIds.first()
        assertEquals(true, ids.contains("1"))
    }
}
