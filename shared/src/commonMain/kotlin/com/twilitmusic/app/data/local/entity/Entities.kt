package com.twilitmusic.app.data.local.entity

import kotlinx.datetime.Clock

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "liked_tracks")
data class LikedTrackEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String,
    val artUrl: String,
    val sourceUrl: String,
    @ColumnInfo(name = "added_at") val addedAt: Long = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
)

@Entity(
    tableName = "playlist_tracks",
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlist_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("playlist_id")]
)
data class PlaylistTrackEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "playlist_id") val playlistId: Long,
    @ColumnInfo(name = "track_id") val trackId: String,
    val title: String,
    val artist: String,
    val artUrl: String,
    val sourceUrl: String,
    val position: Int
)

@Entity(tableName = "play_history")
data class PlayHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "track_id") val trackId: String,
    val title: String,
    val artist: String,
    val artUrl: String,
    val sourceUrl: String,
    @ColumnInfo(name = "played_at") val playedAt: Long = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
)
