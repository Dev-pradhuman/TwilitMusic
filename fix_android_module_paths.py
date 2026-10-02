import re

path = 'shared/src/androidMain/kotlin/com/twilitmusic/app/di/SharedModule.android.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import org.koin.dsl.module', 'import org.koin.dsl.module\nimport com.twilitmusic.app.domain.PlatformPaths\nimport com.twilitmusic.app.domain.AndroidPlatformPaths\nimport org.koin.android.ext.koin.androidContext')

old_db = '''    single<RoomDatabase.Builder<TwilitDatabase>> {
        val dbFile = androidContext().getDatabasePath("twilit_music.db")
        Room.databaseBuilder<TwilitDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath
        )
    }'''

new_db = '''    single<PlatformPaths> { AndroidPlatformPaths(androidContext()) }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            context = androidContext(),
            name = paths.databasePath
        )
    }'''

content = content.replace(old_db, new_db)

with open(path, 'w') as f:
    f.write(content)
