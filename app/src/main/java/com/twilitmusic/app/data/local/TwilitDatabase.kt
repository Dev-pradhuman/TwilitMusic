package com.twilitmusic.app.data.local

import androidx.room.Database
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
        PlayHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TwilitDatabase : RoomDatabase() {
    abstract fun likedTrackDao(): LikedTrackDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun playHistoryDao(): PlayHistoryDao
}
