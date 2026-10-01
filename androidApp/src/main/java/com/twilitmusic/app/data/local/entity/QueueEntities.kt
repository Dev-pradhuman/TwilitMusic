package com.twilitmusic.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "queue_tracks")
data class QueueTrackEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val trackId: String,
    val title: String,
    val artist: String,
    val artUrl: String,
    val sourceUrl: String,
    val position: Int
)

@Entity(tableName = "playback_state")
data class PlaybackStateEntity(
    @PrimaryKey val id: Int = 1, // Singleton
    val currentIndex: Int,
    val positionMs: Long
)
