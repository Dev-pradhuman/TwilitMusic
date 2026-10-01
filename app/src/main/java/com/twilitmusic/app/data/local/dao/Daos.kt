package com.twilitmusic.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Delete
import com.twilitmusic.app.data.local.entity.LikedTrackEntity
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedTrackDao {
    @Query("SELECT * FROM liked_tracks ORDER BY added_at DESC")
    fun getLikedTracks(): Flow<List<LikedTrackEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM liked_tracks WHERE id = :id)")
    fun isLiked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikedTrack(track: LikedTrackEntity)

    @Query("DELETE FROM liked_tracks WHERE id = :id")
    suspend fun removeLikedTrack(id: String)
}

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists ORDER BY created_at DESC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPlaylist(playlist: PlaylistEntity): Long

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    suspend fun deletePlaylist(playlistId: Long)

    @Query("SELECT * FROM playlist_tracks WHERE playlist_id = :playlistId ORDER BY position ASC")
    fun getTracksForPlaylist(playlistId: Long): Flow<List<PlaylistTrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrackToPlaylist(track: PlaylistTrackEntity)

    @Query("DELETE FROM playlist_tracks WHERE id = :playlistTrackId")
    suspend fun removeTrackFromPlaylist(playlistTrackId: Long)
}

@Dao
interface PlayHistoryDao {
    @Query("SELECT * FROM play_history ORDER BY played_at DESC LIMIT :limit")
    fun getPlayHistory(limit: Int = 20): Flow<List<PlayHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayHistory(track: PlayHistoryEntity)
}
