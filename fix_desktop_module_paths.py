import re

path = 'shared/src/desktopMain/kotlin/com/twilitmusic/app/di/SharedModule.desktop.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.TwilitDownloadManager', 'import com.twilitmusic.app.domain.TwilitDownloadManager\nimport com.twilitmusic.app.domain.PlatformPaths\nimport com.twilitmusic.app.domain.DesktopPlatformPaths')

old_db = '''    single<RoomDatabase.Builder<TwilitDatabase>> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "TwilitMusic")
            os.contains("mac") -> File(userHome, "Library/Application Support/TwilitMusic")
            else -> File(System.getenv("XDG_DATA_HOME") ?: "$userHome/.local/share", "TwilitMusic")
        }
        if (!appDataDir.exists()) appDataDir.mkdirs()
        val dbFile = File(appDataDir, "twilit_music.db")
        Room.databaseBuilder<TwilitDatabase>(
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
    }'''

new_db = '''    single<PlatformPaths> { DesktopPlatformPaths() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            name = paths.databasePath
        ).setDriver(BundledSQLiteDriver())
    }'''

content = content.replace(old_db, new_db)

with open(path, 'w') as f:
    f.write(content)
