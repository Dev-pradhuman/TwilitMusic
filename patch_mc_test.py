import sys

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import org.junit.Test', 'import org.junit.Test\nimport com.twilitmusic.app.domain.repository.LibraryRepository')

old_setup = '''    @Before
    fun setup() {
        mockContext = mock()
        controller = MusicController(mockContext)
    }'''
new_setup = '''    @Before
    fun setup() {
        mockContext = mock()
        val mockLibrary = mock<LibraryRepository>()
        controller = MusicController(mockContext, mockLibrary)
    }'''
content = content.replace(old_setup, new_setup)

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'w') as f:
    f.write(content)
