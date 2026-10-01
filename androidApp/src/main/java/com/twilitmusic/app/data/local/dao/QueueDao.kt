package com.twilitmusic.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.twilitmusic.app.data.local.entity.PlaybackStateEntity
import com.twilitmusic.app.data.local.entity.QueueTrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QueueDao {
    @Query("SELECT * FROM queue_tracks ORDER BY position ASC")
    suspend fun getQueue(): List<QueueTrackEntity>

    @Query("SELECT * FROM playback_state WHERE id = 1")
    suspend fun getPlaybackState(): PlaybackStateEntity?

    @Query("DELETE FROM queue_tracks")
    suspend fun clearQueue()

    @Insert
    suspend fun insertQueue(tracks: List<QueueTrackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlaybackState(state: PlaybackStateEntity)

    @Transaction
    suspend fun saveFullState(tracks: List<QueueTrackEntity>, state: PlaybackStateEntity) {
        clearQueue()
        insertQueue(tracks)
        savePlaybackState(state)
    }
}
