import sys

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelDownloadTest.kt', 'r') as f:
    content = f.read()

mock_code = '''
        whenever(mockMusicSource.getFeaturedTracks()).thenReturn(Result.success(emptyList()))
        whenever(mockMusicSource.getNewTracks()).thenReturn(Result.success(emptyList()))
        whenever(mockQueueDao.getQueue()).thenReturn(emptyList())
        viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo, mockQueueDao)'''

content = content.replace('viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo, mockQueueDao)', mock_code)

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelDownloadTest.kt', 'w') as f:
    f.write(content)
