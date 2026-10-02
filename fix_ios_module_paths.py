import re

path = 'shared/src/iosMain/kotlin/com/twilitmusic/app/di/SharedModule.ios.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.domain.TwilitDownloadManager', 'import com.twilitmusic.app.domain.TwilitDownloadManager\nimport com.twilitmusic.app.domain.PlatformPaths\nimport com.twilitmusic.app.domain.IosPlatformPaths')

old_db = '''    single<RoomDatabase.Builder<TwilitDatabase>> {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val dbPath = documentDirectory?.path + "/twilit_music.db"
        Room.databaseBuilder<TwilitDatabase>(
            name = dbPath
        ).setDriver(BundledSQLiteDriver())
    }'''

new_db = '''    single<PlatformPaths> { IosPlatformPaths() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            name = paths.databasePath
        ).setDriver(BundledSQLiteDriver())
    }'''

content = content.replace(old_db, new_db)

with open(path, 'w') as f:
    f.write(content)
