import sys

def patch_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    if '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)' not in content:
        content = content.replace('class CacheManager', '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass CacheManager')
        content = content.replace('class TwilitDownloadService', '@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)\nclass TwilitDownloadService')

    with open(filepath, 'w') as f:
        f.write(content)

patch_file('app/src/main/java/com/twilitmusic/app/playback/CacheManager.kt')
patch_file('app/src/main/java/com/twilitmusic/app/playback/TwilitDownloadService.kt')
