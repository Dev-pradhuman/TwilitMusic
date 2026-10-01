import sys

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelDownloadTest.kt', 'r') as f:
    content = f.read()

import_app = 'import android.app.Application\n'
if import_app not in content:
    content = content.replace('import android.content.Context', import_app + 'import android.content.Context')

content = content.replace('private lateinit var mockContext: Context', 'private lateinit var mockApplication: Application\n    private lateinit var mockContext: Context')
content = content.replace('mockContext = mock()', 'mockApplication = mock()\n        mockContext = mock()')

content = content.replace('viewModel = MainViewModel(mockContext, mockDownloadManager, mockLibraryRepo, mockMusicSource, mockMusicController)', 'viewModel = MainViewModel(mockApplication, mockMusicSource, mockMusicController, mockContext, mockDownloadManager, mockLibraryRepo)')

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelDownloadTest.kt', 'w') as f:
    f.write(content)
