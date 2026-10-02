import re

path = 'shared/src/androidMain/kotlin/com/twilitmusic/app/di/SharedModule.android.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.AndroidPlatformPaths', 'import com.twilitmusic.app.domain.AndroidPlatformPaths\nimport com.twilitmusic.app.domain.ConnectivityMonitor\nimport com.twilitmusic.app.domain.AndroidConnectivityMonitor\nimport com.twilitmusic.app.domain.TwilitDownloadManager\nimport com.twilitmusic.app.domain.AndroidDownloadManager')

content = content.replace('single<PlatformPaths> { AndroidPlatformPaths(androidContext()) }', 'single<PlatformPaths> { AndroidPlatformPaths(androidContext()) }\n    single<ConnectivityMonitor> { AndroidConnectivityMonitor(androidContext()) }\n    single<TwilitDownloadManager> { AndroidDownloadManager(androidContext()) }')

with open(path, 'w') as f:
    f.write(content)
