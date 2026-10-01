package com.twilitmusic.app.data.local

import androidx.room.Database
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.data.local.entity.QueueTrackEntity
import com.twilitmusic.app.data.local.entity.PlaybackStateEntity
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.dao.LikedTrackDao
import com.twilitmusic.app.data.local.dao.PlayHistoryDao
import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.entity.LikedTrackEntity
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity

@Database(
    entities = [
        LikedTrackEntity::class,
        PlaylistEntity::class,
        PlaylistTrackEntity::class,
        PlayHistoryEntity::class,
        QueueTrackEntity::class,
        PlaybackStateEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class TwilitDatabase : RoomDatabase() {
    abstract fun likedTrackDao(): LikedTrackDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun playHistoryDao(): PlayHistoryDao
    abstract fun queueDao(): QueueDao
}
