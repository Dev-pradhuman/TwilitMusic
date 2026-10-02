import glob

# The error was: 
# Argument type mismatch: actual type is 'android.app.Application', but 'com.twilitmusic.app.domain.repository.MusicSource' was expected.
# MainViewModel constructor now:
# (musicSource: MusicSource, musicController: AudioPlayer, downloadManager: TwilitDownloadManager, libraryRepository: LibraryRepository, connectivityMonitor: ConnectivityMonitor, queueDao: QueueDao)

def fix_file(path):
    with open(path, 'r') as f:
        content = f.read()

    # In tests, they used: viewModel = MainViewModel(application, musicController, libraryRepository, queueDao)
    # or similar.
    # We need to replace it with mock objects for MusicSource, AudioPlayer, TwilitDownloadManager, LibraryRepository, ConnectivityMonitor, QueueDao.

    content = content.replace("import com.twilitmusic.app.playback.MusicController", "import com.twilitmusic.app.playback.AudioPlayer")
    content = content.replace("private lateinit var musicController: MusicController", "private lateinit var musicController: AudioPlayer")
    content = content.replace("private lateinit var application: Application", "private lateinit var musicSource: com.twilitmusic.app.domain.repository.MusicSource\n    private lateinit var downloadManager: com.twilitmusic.app.domain.TwilitDownloadManager\n    private lateinit var connectivityMonitor: com.twilitmusic.app.domain.ConnectivityMonitor")
    content = content.replace("application = mock()", "musicSource = mock()\n        downloadManager = mock()\n        connectivityMonitor = mock()")
    
    # Replace the view model instantiation
    content = content.replace("viewModel = MainViewModel(\n            application,\n            musicController,\n            libraryRepository,\n            queueDao\n        )", "viewModel = MainViewModel(musicSource, musicController, downloadManager, libraryRepository, connectivityMonitor, queueDao)")
    content = content.replace("viewModel = MainViewModel(application, musicController, libraryRepository, queueDao)", "viewModel = MainViewModel(musicSource, musicController, downloadManager, libraryRepository, connectivityMonitor, queueDao)")
    
    with open(path, 'w') as f:
        f.write(content)

for f in glob.glob("androidApp/src/test/java/com/twilitmusic/app/ui/MainViewModel*Test.kt"):
    fix_file(f)

