import os
import re

vm_path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainViewModel.kt'
with open(vm_path, 'r') as f:
    content = f.read()

# Remove Android imports
content = re.sub(r'import android\..+\n', '', content)
content = re.sub(r'import androidx\.media3\..+\n', '', content)
content = re.sub(r'import com\.twilitmusic\.app\.playback\.TwilitDownloadService\n', '', content)
content = re.sub(r'import dagger\.hilt\.android\.qualifiers\.ApplicationContext\n', '', content)
content = re.sub(r'import com\.twilitmusic\.app\.playback\.MusicController\n', 'import com.twilitmusic.app.playback.AudioPlayer\nimport com.twilitmusic.app.domain.ConnectivityMonitor\nimport com.twilitmusic.app.domain.TwilitDownloadManager\n', content)

# Remove annotations
content = re.sub(r'@androidx\.annotation\.OptIn\(androidx\.media3\.common\.util\.UnstableApi::class\)\n', '', content)

# Replace constructor parameters
content = re.sub(r'class MainViewModel \(\n.*\) : ViewModel\(\)', '''class MainViewModel (
    private val musicSource: MusicSource,
    val musicController: AudioPlayer,
    private val downloadManager: TwilitDownloadManager,
    private val libraryRepository: LibraryRepository,
    private val connectivityMonitor: ConnectivityMonitor,
    private val queueDao: QueueDao
) : ViewModel()''', content, flags=re.DOTALL)

# Replace ConnectivityManager logic
init_block = '''init {
        viewModelScope.launch {
            connectivityMonitor.isOffline.collect { offline ->
                isOffline.value = offline
            }
        }
        
        viewModelScope.launch {
            musicController.init()
            loadHomeData()
        }
    }'''
content = re.sub(r'init \{.*loadHomeData\(\)\n\s*\}\n\s*\}', init_block, content, flags=re.DOTALL)

# Replace downloadManager logic
download_block = '''fun downloadTrack(track: Track) {
        if (track.sourceUrl.isEmpty()) return
        downloadManager.download(track)
    }'''
content = re.sub(r'fun downloadTrack.*?\}', download_block, content, flags=re.DOTALL)

with open(vm_path, 'w') as f:
    f.write(content)

