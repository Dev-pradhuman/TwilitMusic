import sys

def process(file_path):
    with open(file_path, 'r') as f:
        content = f.read()

    # fix queue dao mocks
    content = content.replace('whenever(mockQueueDao.getQueue()).thenReturn(Result.success(emptyList()))', 'whenever(mockQueueDao.getQueue()).thenReturn(emptyList())')
    content = content.replace('whenever(mockQueueDao.getQueue()).thenReturn(Result.success(listOf(', 'whenever(mockQueueDao.getQueue()).thenReturn(listOf(')

    # fix track mock
    content = content.replace('whenever(mockMusicSource.getFeaturedTracks()).thenReturn(listOf(', 'whenever(mockMusicSource.getFeaturedTracks()).thenReturn(Result.success(listOf(')
    content = content.replace('whenever(mockMusicSource.getNewTracks()).thenReturn(listOf(', 'whenever(mockMusicSource.getNewTracks()).thenReturn(Result.success(listOf(')

    # fix constructors
    content = content.replace(
        'MainViewModel(application, mockMusicSource, mockMusicController, libraryRepository, queueDao)',
        'MainViewModel(application, mockMusicSource, mockMusicController, org.mockito.Mockito.mock(android.content.Context::class.java), org.mockito.Mockito.mock(androidx.media3.exoplayer.offline.DownloadManager::class.java), libraryRepository, queueDao)'
    )
    
    with open(file_path, 'w') as f:
        f.write(content)

process('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt')
process('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt')
