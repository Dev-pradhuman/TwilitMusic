import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.media3.common.MediaItem', 'import androidx.media3.common.MediaItem\nimport com.twilitmusic.app.data.local.dao.QueueDao')

old_ctor = '''class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController,
    private val libraryRepository: LibraryRepository
) : ViewModel() {'''
new_ctor = '''class MainViewModel @Inject constructor(
    private val application: Application,
    private val musicSource: MusicSource,
    val musicController: MusicController,
    private val libraryRepository: LibraryRepository,
    private val queueDao: QueueDao
) : ViewModel() {'''
content = content.replace(old_ctor, new_ctor)

old_restore = '''    private suspend fun restoreQueue() {
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
    }'''
new_restore = '''    private suspend fun restoreQueue() {
        if (musicController.queue.value.isNotEmpty()) return
        
        // 1. Check Room
        val queueEntities = queueDao.getQueue()
        val state = queueDao.getPlaybackState()
        
        if (queueEntities.isNotEmpty()) {
            val tracks = queueEntities.map { Track(it.trackId, it.title, it.artist, it.artUrl, it.sourceUrl) }
            val index = state?.currentIndex ?: 0
            val pos = state?.positionMs ?: 0L
            musicController.setQueueWithoutPlaying(tracks, index)
            if (pos > 0) {
                musicController.seekTo(pos)
            }
            return
        }
        
        // 2. Migration from SharedPreferences
        val prefs = application.getSharedPreferences("music_prefs", Context.MODE_PRIVATE)
        val trackIdsStr = prefs.getString("saved_queue", "") ?: ""
        if (trackIdsStr.isNotEmpty()) {
            val index = prefs.getInt("saved_index", 0)
            val trackIds = trackIdsStr.split(",")
            val allTracks = musicSource.getFeaturedTracks() + musicSource.getNewTracks()
            val restored = trackIds.mapNotNull { id -> allTracks.find { it.id == id } }
            if (restored.isNotEmpty()) {
                musicController.setQueueWithoutPlaying(restored, index)
            }
            prefs.edit().remove("saved_queue").remove("saved_index").apply()
        }
    }'''
content = content.replace(old_restore, new_restore)

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
