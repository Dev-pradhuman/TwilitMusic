import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.lifecycle.ViewModel', 'import androidx.lifecycle.ViewModel\nimport androidx.media3.exoplayer.offline.DownloadRequest\nimport androidx.media3.exoplayer.offline.DownloadService\nimport com.twilitmusic.app.playback.TwilitDownloadService\nimport android.content.Context\nimport android.net.Uri\nimport dagger.hilt.android.qualifiers.ApplicationContext\nimport androidx.media3.common.MediaItem\nimport androidx.media3.exoplayer.offline.DownloadManager')

if '@ApplicationContext private val context: Context,' not in content:
    content = content.replace('private val libraryRepository: LibraryRepository', '@ApplicationContext private val context: Context,\n    private val downloadManager: DownloadManager,\n    private val libraryRepository: LibraryRepository')

new_methods = '''
    fun downloadTrack(track: Track) {
        if (track.sourceUrl.isEmpty()) return
        val downloadRequest = DownloadRequest.Builder(track.id, Uri.parse(track.sourceUrl)).build()
        DownloadService.sendAddDownload(context, TwilitDownloadService::class.java, downloadRequest, false)
    }

    val downloadedTrackIds = MutableStateFlow<Set<String>>(emptySet())
    
    // In a real app we would observe downloadManager.addListener, but for now we poll or rely on UI updates
'''

if 'fun downloadTrack' not in content:
    content = content.replace('fun playTrack(', new_methods + '\n    fun playTrack(')

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
