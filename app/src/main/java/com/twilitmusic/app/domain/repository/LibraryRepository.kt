package com.twilitmusic.app.domain.repository

import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {
    fun getLikedTracks(): Flow<List<Track>>
    fun isLiked(trackId: String): Flow<Boolean>
    suspend fun toggleLike(track: Track, isLiked: Boolean)
    
    fun getPlayHistory(): Flow<List<Track>>
    suspend fun addPlayHistory(track: Track)
}
