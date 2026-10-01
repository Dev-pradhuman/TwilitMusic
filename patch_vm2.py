import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.media3.common.MediaItem', 'import androidx.media3.common.MediaItem\nimport com.twilitmusic.app.domain.repository.LibraryRepository\nimport kotlinx.coroutines.flow.flatMapLatest\nimport kotlinx.coroutines.flow.flowOf\nimport kotlinx.coroutines.flow.SharingStarted\nimport kotlinx.coroutines.flow.stateIn\nimport kotlinx.coroutines.ExperimentalCoroutinesApi')

old_ctor = '''class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController
) : ViewModel() {'''
new_ctor = '''class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController,
    private val libraryRepository: LibraryRepository
) : ViewModel() {'''
content = content.replace(old_ctor, new_ctor)

old_init = '''    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()'''
new_init = '''    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    
    @OptIn(ExperimentalCoroutinesApi::class)
    val isCurrentTrackLiked = musicController.currentTrack.flatMapLatest { track ->
        if (track == null) flowOf(false) else libraryRepository.isLiked(track.id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    
    fun toggleLike() {
        val track = musicController.currentTrack.value ?: return
        viewModelScope.launch {
            libraryRepository.toggleLike(track, !isCurrentTrackLiked.value)
        }
    }'''
content = content.replace(old_init, new_init)

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
