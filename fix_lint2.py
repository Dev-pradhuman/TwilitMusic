import sys

def process(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # ensure the class is annotated
    content = content.replace('@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass', '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass')
    
    # but wait, maybe it was not applied?
    if '@androidx.annotation.OptIn' not in content:
        content = content.replace('class CacheManager', '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass CacheManager')
        content = content.replace('class TwilitDownloadService', '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass TwilitDownloadService')
        
    with open(filepath, 'w') as f:
        f.write(content)

process('app/src/main/java/com/twilitmusic/app/playback/CacheManager.kt')
process('app/src/main/java/com/twilitmusic/app/playback/TwilitDownloadService.kt')
process('app/src/main/java/com/twilitmusic/app/di/DownloadModule.kt')
process('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt')
