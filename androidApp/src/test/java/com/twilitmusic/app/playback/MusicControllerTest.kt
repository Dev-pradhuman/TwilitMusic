package com.twilitmusic.app.playback

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.domain.repository.LibraryRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MusicControllerTest {

    private lateinit var controller: MusicController
    private lateinit var mockContext: Context
    private lateinit var mockMediaController: MediaController

    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        val mockLibrary = mock(LibraryRepository::class.java)
        val mockQueueDao = mock(QueueDao::class.java)
        controller = MusicController(mockContext, mockLibrary, mockQueueDao)
        
        mockMediaController = mock(MediaController::class.java)
        whenever(mockMediaController.shuffleModeEnabled).thenReturn(false)
        whenever(mockMediaController.repeatMode).thenReturn(Player.REPEAT_MODE_OFF)
        controller.mediaController = mockMediaController
    }

    @Test
    fun testInitialization() {
        assertNotNull(controller)
    }

    @Test
    fun testShuffleToggle() {
        controller.toggleShuffle()
        verify(mockMediaController).shuffleModeEnabled = true
    }

    @Test
    fun testRepeatCycle() {
        controller.cycleRepeatMode()
        verify(mockMediaController).repeatMode = Player.REPEAT_MODE_ALL
    }
}
