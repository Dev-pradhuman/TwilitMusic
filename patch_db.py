import sys

with open('app/src/main/java/com/twilitmusic/app/data/local/TwilitDatabase.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.room.Database', 'import androidx.room.Database\nimport androidx.room.migration.Migration\nimport androidx.sqlite.db.SupportSQLiteDatabase\nimport com.twilitmusic.app.data.local.dao.QueueDao\nimport com.twilitmusic.app.data.local.entity.QueueTrackEntity\nimport com.twilitmusic.app.data.local.entity.PlaybackStateEntity')

old_entities = '''    entities = [
        LikedTrackEntity::class,
        PlaylistEntity::class,
        PlaylistTrackEntity::class,
        PlayHistoryEntity::class
    ],
    version = 1,'''
new_entities = '''    entities = [
        LikedTrackEntity::class,
        PlaylistEntity::class,
        PlaylistTrackEntity::class,
        PlayHistoryEntity::class,
        QueueTrackEntity::class,
        PlaybackStateEntity::class
    ],
    version = 2,'''
content = content.replace(old_entities, new_entities)

content = content.replace('abstract fun playHistoryDao(): PlayHistoryDao', 'abstract fun playHistoryDao(): PlayHistoryDao\n    abstract fun queueDao(): QueueDao')

with open('app/src/main/java/com/twilitmusic/app/data/local/TwilitDatabase.kt', 'w') as f:
    f.write(content)
