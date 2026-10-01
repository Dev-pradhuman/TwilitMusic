import sys

with open('app/src/main/java/com/twilitmusic/app/di/DatabaseModule.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.room.Room', 'import androidx.room.Room\nimport androidx.room.migration.Migration\nimport androidx.sqlite.db.SupportSQLiteDatabase\nimport com.twilitmusic.app.data.local.dao.QueueDao')

old_prov = '''    @Provides
    @Singleton
    fun provideTwilitDatabase(@ApplicationContext context: Context): TwilitDatabase {
        return Room.databaseBuilder(
            context,
            TwilitDatabase::class.java,
            "twilit_music.db"
        ).build()
    }'''

new_prov = '''    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS `queue_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trackId` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `playback_state` (`id` INTEGER NOT NULL, `currentIndex` INTEGER NOT NULL, `positionMs` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        }
    }

    @Provides
    @Singleton
    fun provideTwilitDatabase(@ApplicationContext context: Context): TwilitDatabase {
        return Room.databaseBuilder(
            context,
            TwilitDatabase::class.java,
            "twilit_music.db"
        )
        .addMigrations(MIGRATION_1_2)
        .build()
    }'''

content = content.replace(old_prov, new_prov)
content += '\n    @Provides\n    fun provideQueueDao(database: TwilitDatabase): QueueDao = database.queueDao()'

with open('app/src/main/java/com/twilitmusic/app/di/DatabaseModule.kt', 'w') as f:
    f.write(content)
