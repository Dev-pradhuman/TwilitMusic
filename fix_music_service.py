import re

path = 'androidApp/src/main/java/com/twilitmusic/app/playback/MusicService.kt'
with open(path, 'r') as f:
    content = f.read()

# Imports
content = re.sub(r'import dagger\.hilt\..*\n', '', content)
content = re.sub(r'import javax\.inject\..*\n', '', content)

# Annotations & Constructor
content = re.sub(r'@AndroidEntryPoint\nclass MusicService : MediaSessionService\(\) \{', 'class MusicService : MediaSessionService() {', content, flags=re.DOTALL)
content = re.sub(r'@Inject\n\s*lateinit var cacheManager: CacheManager', 'val cacheManager: CacheManager by org.koin.android.ext.android.inject()', content)
content = re.sub(r'@Inject\n\s*lateinit var libraryRepository: LibraryRepository', 'val libraryRepository: LibraryRepository by org.koin.android.ext.android.inject()', content)
content = re.sub(r'@Inject\n\s*lateinit var queueDao: QueueDao', 'val queueDao: QueueDao by org.koin.android.ext.android.inject()', content)

# We need to import Koin inject
content = content.replace('import androidx.media3.session.MediaSessionService', 'import androidx.media3.session.MediaSessionService\nimport org.koin.android.ext.android.inject')

with open(path, 'w') as f:
    f.write(content)

