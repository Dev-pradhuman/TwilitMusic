import sys

with open('app/src/main/java/com/twilitmusic/app/di/DataModule.kt', 'r') as f:
    content = f.read()

content = content.replace('import com.twilitmusic.app.data.repository.DemoMusicSource', 'import com.twilitmusic.app.data.repository.DemoMusicSource\nimport com.twilitmusic.app.data.repository.LibraryRepositoryImpl\nimport com.twilitmusic.app.domain.repository.LibraryRepository')

old_bind = '''    @Binds
    abstract fun bindMusicSource(
        demoMusicSource: DemoMusicSource
    ): MusicSource
}'''
new_bind = '''    @Binds
    abstract fun bindMusicSource(
        demoMusicSource: DemoMusicSource
    ): MusicSource

    @Binds
    abstract fun bindLibraryRepository(
        libraryRepositoryImpl: LibraryRepositoryImpl
    ): LibraryRepository
}'''
content = content.replace(old_bind, new_bind)

with open('app/src/main/java/com/twilitmusic/app/di/DataModule.kt', 'w') as f:
    f.write(content)
