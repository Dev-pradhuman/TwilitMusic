import sys

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import org.mockito.Mockito.mock', 'import org.mockito.Mockito.mock\nimport androidx.media3.session.MediaController\nimport org.mockito.kotlin.whenever')

old_setup = '''    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        val mockLibrary = mock(LibraryRepository::class.java)
        val mockQueueDao = mock(QueueDao::class.java)
        controller = MusicController(mockContext, mockLibrary, mockQueueDao)
    }'''

new_setup = '''    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        val mockLibrary = mock(LibraryRepository::class.java)
        val mockQueueDao = mock(QueueDao::class.java)
        controller = MusicController(mockContext, mockLibrary, mockQueueDao)
        val mockMediaController = mock(MediaController::class.java)
        whenever(mockMediaController.shuffleModeEnabled).thenReturn(false)
        whenever(mockMediaController.repeatMode).thenReturn(androidx.media3.common.Player.REPEAT_MODE_OFF)
        controller.mediaController = mockMediaController
    }'''
content = content.replace(old_setup, new_setup)

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'w') as f:
    f.write(content)
