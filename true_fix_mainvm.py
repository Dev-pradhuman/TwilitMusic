import re

vm_path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainViewModel.kt'
with open(vm_path, 'r') as f:
    content = f.read()

# Replace constructor correctly
old_constructor = r'@HiltViewModel\nclass MainViewModel @Inject constructor\([\s\S]*?\) : ViewModel\(\)'
new_constructor = '''class MainViewModel (
    private val musicSource: MusicSource,
    val musicController: AudioPlayer,
    private val downloadManager: TwilitDownloadManager,
    private val libraryRepository: LibraryRepository,
    private val connectivityMonitor: ConnectivityMonitor,
    private val queueDao: QueueDao
) : ViewModel()'''

content = re.sub(old_constructor, new_constructor, content, flags=re.DOTALL)

# Remove Hilt imports
content = re.sub(r'import dagger\.hilt\..*\n', '', content)
content = re.sub(r'import javax\.inject\.Inject\n', '', content)
content = re.sub(r'import android\.app\.Application\n', '', content)

with open(vm_path, 'w') as f:
    f.write(content)

