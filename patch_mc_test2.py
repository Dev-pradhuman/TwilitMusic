import sys

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'r') as f:
    content = f.read()

old_setup = '''    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        controller = MusicController(mockContext)
    }'''
new_setup = '''    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        val mockLibrary = mock(LibraryRepository::class.java)
        controller = MusicController(mockContext, mockLibrary)
    }'''
content = content.replace(old_setup, new_setup)

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'w') as f:
    f.write(content)
