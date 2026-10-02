import re

path = 'shared/src/desktopMain/kotlin/com/twilitmusic/app/di/SharedModule.desktop.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import kotlinx.coroutines.Dispatchers', 'import kotlinx.coroutines.Dispatchers\nimport com.twilitmusic.app.playback.AudioPlayer\nimport com.twilitmusic.app.playback.DesktopAudioPlayer\nimport com.twilitmusic.app.domain.ConnectivityMonitor\nimport com.twilitmusic.app.domain.DesktopConnectivityMonitor\nimport com.twilitmusic.app.domain.TwilitDownloadManager\nimport com.twilitmusic.app.domain.DesktopDownloadManager')

content = content.replace(').setDriver(BundledSQLiteDriver())\n    }\n}', ').setDriver(BundledSQLiteDriver())\n    }\n    single<AudioPlayer> { DesktopAudioPlayer() }\n    single<ConnectivityMonitor> { DesktopConnectivityMonitor() }\n    single<TwilitDownloadManager> { DesktopDownloadManager() }\n}')

with open(path, 'w') as f:
    f.write(content)
