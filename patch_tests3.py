import sys

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.repository.LibraryRepository', 'import com.twilitmusic.app.domain.repository.LibraryRepository\nimport com.twilitmusic.app.data.local.dao.QueueDao')
content = content.replace('private lateinit var libraryRepository: LibraryRepository', 'private lateinit var libraryRepository: LibraryRepository\n    private lateinit var queueDao: QueueDao')

content = content.replace('libraryRepository = mock()', 'libraryRepository = mock()\n        queueDao = mock()')
content = content.replace('whenever(musicSource.getFeaturedTracks()).thenReturn(emptyList())', 'whenever(queueDao.getQueue()).thenReturn(emptyList())\n        whenever(queueDao.getPlaybackState()).thenReturn(null)\n        whenever(musicSource.getFeaturedTracks()).thenReturn(emptyList())')
content = content.replace('viewModel = MainViewModel(application, musicSource, musicController, libraryRepository)', 'viewModel = MainViewModel(application, musicSource, musicController, libraryRepository, queueDao)')

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt', 'w') as f:
    f.write(content)

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'r') as f:
    content = f.read()
content = content.replace('import com.twilitmusic.app.domain.repository.LibraryRepository', 'import com.twilitmusic.app.domain.repository.LibraryRepository\nimport com.twilitmusic.app.data.local.dao.QueueDao')
content = content.replace('val mockLibrary = mock(LibraryRepository::class.java)', 'val mockLibrary = mock(LibraryRepository::class.java)\n        val mockQueueDao = mock(QueueDao::class.java)')
content = content.replace('controller = MusicController(mockContext, mockLibrary)', 'controller = MusicController(mockContext, mockLibrary, mockQueueDao)')
with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'w') as f:
    f.write(content)
