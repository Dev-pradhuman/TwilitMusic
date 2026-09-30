package com.twilitmusic.app.domain.repository

import com.twilitmusic.app.domain.model.Track

interface MusicSource {
    suspend fun search(query: String): List<Track>
    suspend fun getTrack(id: String): Track?
    suspend fun getStreamUrl(id: String): String?
    suspend fun getFeaturedTracks(): List<Track>
    suspend fun getNewTracks(): List<Track>
}
