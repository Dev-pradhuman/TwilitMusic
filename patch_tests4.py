import sys

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.repository.LibraryRepository', 'import com.twilitmusic.app.domain.repository.LibraryRepository\nimport com.twilitmusic.app.data.local.dao.QueueDao')
content = content.replace('private lateinit var libraryRepository: LibraryRepository', 'private lateinit var libraryRepository: LibraryRepository\n    private lateinit var queueDao: QueueDao')

content = content.replace('libraryRepository = mock()', 'libraryRepository = mock()\n        queueDao = mock()')
content = content.replace('whenever(libraryRepository.isLiked(org.mockito.kotlin.any())).thenReturn(kotlinx.coroutines.flow.MutableStateFlow(false))', 'whenever(libraryRepository.isLiked(org.mockito.kotlin.any())).thenReturn(kotlinx.coroutines.flow.MutableStateFlow(false))\n        whenever(queueDao.getQueue()).thenReturn(emptyList())\n        whenever(queueDao.getPlaybackState()).thenReturn(null)')

content = content.replace('viewModel = MainViewModel(application, mockMusicSource, mockMusicController, libraryRepository)', 'viewModel = MainViewModel(application, mockMusicSource, mockMusicController, libraryRepository, queueDao)')

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt', 'w') as f:
    f.write(content)
