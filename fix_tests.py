import sys
import glob

def fix_main_view_model_test(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Fix mock returns
    content = content.replace('thenReturn(emptyList())', 'thenReturn(Result.success(emptyList()))')
    content = content.replace('thenReturn(listOf(track))', 'thenReturn(Result.success(listOf(track)))')

    # Fix MainViewModel constructors
    # It was: MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockLibraryRepo, mockQueueDao)
    # New: MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo, mockQueueDao)
    content = content.replace(
        'MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockLibraryRepo, mockQueueDao)',
        'MainViewModel(mockApplication, mockMusicSource, mockMusicController, org.mockito.Mockito.mock(android.content.Context::class.java), org.mockito.Mockito.mock(androidx.media3.exoplayer.offline.DownloadManager::class.java), mockLibraryRepo, mockQueueDao)'
    )
    
    with open(filepath, 'w') as f:
        f.write(content)

fix_main_view_model_test('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt')
fix_main_view_model_test('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt')

def fix_download_test(filepath):
    with open(filepath, 'r') as f:
        content = f.read()
    
    content = content.replace(
        'viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo)',
        'viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo, org.mockito.Mockito.mock(com.twilitmusic.app.data.local.dao.QueueDao::class.java))'
    )
    
    with open(filepath, 'w') as f:
        f.write(content)

fix_download_test('app/src/test/java/com/twilitmusic/app/ui/MainViewModelDownloadTest.kt')

def fix_jamendo_test(filepath):
    with open(filepath, 'r') as f:
        content = f.read()
    
    content = content.replace('val results = source.search("Test")', 'val results = source.search("Test").getOrThrow()')
    
    with open(filepath, 'w') as f:
        f.write(content)

fix_jamendo_test('app/src/test/java/com/twilitmusic/app/data/repository/JamendoMusicSourceTest.kt')
