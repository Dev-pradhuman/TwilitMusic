import re

path = 'shared/src/iosMain/kotlin/com/twilitmusic/app/di/SharedModule.ios.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import kotlinx.coroutines.Dispatchers', 'import kotlinx.coroutines.Dispatchers\nimport com.twilitmusic.app.playback.AudioPlayer\nimport com.twilitmusic.app.playback.IosAudioPlayer\nimport com.twilitmusic.app.domain.ConnectivityMonitor\nimport com.twilitmusic.app.domain.IosConnectivityMonitor\nimport com.twilitmusic.app.domain.TwilitDownloadManager\nimport com.twilitmusic.app.domain.IosDownloadManager')

content = content.replace(').setDriver(BundledSQLiteDriver())\n    }\n}', ').setDriver(BundledSQLiteDriver())\n    }\n    single<AudioPlayer> { IosAudioPlayer() }\n    single<ConnectivityMonitor> { IosConnectivityMonitor() }\n    single<TwilitDownloadManager> { IosDownloadManager() }\n}')

with open(path, 'w') as f:
    f.write(content)
