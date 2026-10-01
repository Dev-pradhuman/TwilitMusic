import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.lifecycle.ViewModel', 'import androidx.lifecycle.ViewModel\nimport android.app.Application\nimport android.content.Context\nimport androidx.media3.common.MediaItem')

old_ctor = '''class MainViewModel @Inject constructor(
    private val musicSource: MusicSource,
    val musicController: MusicController
) : ViewModel() {'''
new_ctor = '''class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController
) : ViewModel() {'''
content = content.replace(old_ctor, new_ctor)

old_init = '''    init {
        loadHomeFeed()
        viewModelScope.launch {
            musicController.init()
        }
    }'''
new_init = '''    init {
        loadHomeFeed()
        viewModelScope.launch {
            musicController.init()
            restoreQueue()
        }
    }
    
    private suspend fun restoreQueue() {
        if (musicController.queue.value.isNotEmpty()) return
        val prefs = application.getSharedPreferences("music_prefs", Context.MODE_PRIVATE)
        val trackIdsStr = prefs.getString("saved_queue", "") ?: ""
        val index = prefs.getInt("saved_index", 0)
        
        if (trackIdsStr.isNotEmpty()) {
            val trackIds = trackIdsStr.split(",")
            val allTracks = musicSource.getFeaturedTracks() + musicSource.getNewTracks() // We just use home feed tracks for now since it's DemoMusicSource
            val restored = trackIds.mapNotNull { id -> allTracks.find { it.id == id } }
            if (restored.isNotEmpty()) {
                musicController.setQueueWithoutPlaying(restored, index)
            }
        }
    }
    
    fun saveQueue() {
        val prefs = application.getSharedPreferences("music_prefs", Context.MODE_PRIVATE)
        val trackIds = musicController.queue.value.joinToString(",") { it.id }
        // For index, we don't have it directly exposed if not playing, but we can assume currentTrack is at some index
        val currentTrack = musicController.currentTrack.value
        val index = musicController.queue.value.indexOfFirst { it.id == currentTrack?.id }.coerceAtLeast(0)
        prefs.edit()
            .putString("saved_queue", trackIds)
            .putInt("saved_index", index)
            .apply()
    }
'''
content = content.replace(old_init, new_init)

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
