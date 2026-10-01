import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.repository.LibraryRepository', 'import com.twilitmusic.app.domain.repository.LibraryRepository\nimport com.twilitmusic.app.data.local.dao.QueueDao\nimport com.twilitmusic.app.data.local.entity.QueueTrackEntity\nimport com.twilitmusic.app.data.local.entity.PlaybackStateEntity')

old_ctor = '''class MusicController @Inject constructor(
    @ApplicationContext private val context: Context,
    private val libraryRepository: LibraryRepository
) {'''
new_ctor = '''class MusicController @Inject constructor(
    @ApplicationContext private val context: Context,
    private val libraryRepository: LibraryRepository,
    private val queueDao: QueueDao
) {'''
content = content.replace(old_ctor, new_ctor)

old_save = '''    private fun saveQueueState() {
        val prefs = context.getSharedPreferences("music_prefs", android.content.Context.MODE_PRIVATE)
        val trackIds = _queue.value.joinToString(",") { it.id }
        val index = mediaController?.currentMediaItemIndex ?: 0
        prefs.edit()
            .putString("saved_queue", trackIds)
            .putInt("saved_index", index)
            .apply()
    }'''
new_save = '''    private fun saveQueueState() {
        val tracks = _queue.value.mapIndexed { index, track -> 
            QueueTrackEntity(trackId = track.id, title = track.title, artist = track.artist, artUrl = track.artUrl, sourceUrl = track.sourceUrl, position = index)
        }
        val currentIndex = mediaController?.currentMediaItemIndex ?: 0
        val pos = mediaController?.currentPosition ?: 0L
        CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            queueDao.saveFullState(tracks, PlaybackStateEntity(id = 1, currentIndex = currentIndex, positionMs = pos))
        }
    }'''
content = content.replace(old_save, new_save)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
