import sys

def fix_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # QueueDao doesn't return Result
    content = content.replace('thenReturn(Result.success(emptyList()))', 'thenReturn(emptyList())')
    content = content.replace('thenReturn(Result.success(listOf(', 'thenReturn(listOf(')

    # MusicSource does return Result
    content = content.replace('whenever(musicSource.getFeaturedTracks()).thenReturn(emptyList())', 'whenever(musicSource.getFeaturedTracks()).thenReturn(Result.success(emptyList()))')
    content = content.replace('whenever(mockMusicSource.getFeaturedTracks()).thenReturn(emptyList())', 'whenever(mockMusicSource.getFeaturedTracks()).thenReturn(Result.success(emptyList()))')
    content = content.replace('whenever(musicSource.getNewTracks()).thenReturn(emptyList())', 'whenever(musicSource.getNewTracks()).thenReturn(Result.success(emptyList()))')
    content = content.replace('whenever(mockMusicSource.getNewTracks()).thenReturn(emptyList())', 'whenever(mockMusicSource.getNewTracks()).thenReturn(Result.success(emptyList()))')

    content = content.replace('whenever(musicSource.getFeaturedTracks()).thenReturn(listOf(', 'whenever(musicSource.getFeaturedTracks()).thenReturn(Result.success(listOf(')
    content = content.replace('whenever(musicSource.getNewTracks()).thenReturn(listOf(', 'whenever(musicSource.getNewTracks()).thenReturn(Result.success(listOf(')

    # Constructors
    content = content.replace(
        'MainViewModel(application, musicSource, musicController, libraryRepository, queueDao)',
        'MainViewModel(application, musicSource, musicController, org.mockito.Mockito.mock(android.content.Context::class.java), org.mockito.Mockito.mock(androidx.media3.exoplayer.offline.DownloadManager::class.java), libraryRepository, queueDao)'
    )

    with open(filepath, 'w') as f:
        f.write(content)

fix_file('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt')
fix_file('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt')
