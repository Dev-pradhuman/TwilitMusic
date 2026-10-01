import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.model.Track', 'import com.twilitmusic.app.domain.model.Track\nimport com.twilitmusic.app.domain.repository.LibraryRepository')

old_ctor = '''class MusicController @Inject constructor(
    @ApplicationContext private val context: Context
) {'''
new_ctor = '''class MusicController @Inject constructor(
    @ApplicationContext private val context: Context,
    private val libraryRepository: LibraryRepository
) {'''
content = content.replace(old_ctor, new_ctor)

old_trans = '''            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentTrack(mediaItem)
            }'''
new_trans = '''            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentTrack(mediaItem)
                mediaItem?.let {
                    CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                        _queue.value.find { track -> track.id == it.mediaId }?.let { track ->
                            libraryRepository.addPlayHistory(track)
                        }
                    }
                }
            }'''
content = content.replace(old_trans, new_trans)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
