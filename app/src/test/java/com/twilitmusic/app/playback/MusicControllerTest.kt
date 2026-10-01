package com.twilitmusic.app.playback

import android.content.Context
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import com.twilitmusic.app.domain.repository.LibraryRepository
import org.mockito.Mockito.mock

class MusicControllerTest {

    private lateinit var controller: MusicController
    private lateinit var mockContext: Context

    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        val mockLibrary = mock(LibraryRepository::class.java)
        controller = MusicController(mockContext, mockLibrary)
    }

    @Test
    fun testInitialization() {
        assertNotNull(controller)
    }
}
