import sys

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.playback.MusicController', 'import com.twilitmusic.app.playback.MusicController\nimport android.app.Application\nimport com.twilitmusic.app.domain.repository.LibraryRepository')

old_setup = '''    private lateinit var mockMusicSource: MusicSource
    private lateinit var mockMusicController: MusicController
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() = runTest {
        Dispatchers.setMain(testDispatcher)
        mockMusicSource = mock()
        mockMusicController = mock()
        
        whenever(mockMusicSource.getFeaturedTracks()).thenReturn(emptyList())
        whenever(mockMusicSource.getNewTracks()).thenReturn(emptyList())
        
        viewModel = MainViewModel(mockMusicSource, mockMusicController)
    }'''
new_setup = '''    private lateinit var mockMusicSource: MusicSource
    private lateinit var mockMusicController: MusicController
    private lateinit var application: Application
    private lateinit var libraryRepository: LibraryRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() = runTest {
        Dispatchers.setMain(testDispatcher)
        mockMusicSource = mock()
        mockMusicController = mock()
        application = mock()
        libraryRepository = mock()
        
        whenever(mockMusicSource.getFeaturedTracks()).thenReturn(emptyList())
        whenever(mockMusicSource.getNewTracks()).thenReturn(emptyList())
        
        val prefs = mock<android.content.SharedPreferences>()
        whenever(application.getSharedPreferences(org.mockito.kotlin.any(), org.mockito.kotlin.any())).thenReturn(prefs)
        whenever(prefs.getString(org.mockito.kotlin.any(), org.mockito.kotlin.any())).thenReturn("")
        whenever(prefs.getInt(org.mockito.kotlin.any(), org.mockito.kotlin.any())).thenReturn(0)
        
        val currentTrackFlow = kotlinx.coroutines.flow.MutableStateFlow<Track?>(null)
        whenever(mockMusicController.currentTrack).thenReturn(currentTrackFlow)
        whenever(mockMusicController.queue).thenReturn(kotlinx.coroutines.flow.MutableStateFlow(emptyList()))
        whenever(libraryRepository.isLiked(org.mockito.kotlin.any())).thenReturn(kotlinx.coroutines.flow.MutableStateFlow(false))
        
        viewModel = MainViewModel(application, mockMusicSource, mockMusicController, libraryRepository)
    }'''
content = content.replace(old_setup, new_setup)

content = content.replace('viewModel = MainViewModel(mockMusicSource, mockMusicController)', 'viewModel = MainViewModel(application, mockMusicSource, mockMusicController, libraryRepository)')

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelTest.kt', 'w') as f:
    f.write(content)
