package com.twilitmusic.app.domain.repository

import com.twilitmusic.app.domain.model.Track

interface MusicSource {
    suspend fun search(query: String): Result<List<Track>>
    suspend fun getTrack(id: String): Result<Track?>
    suspend fun getStreamUrl(id: String): Result<String?>
    suspend fun getFeaturedTracks(): Result<List<Track>>
    suspend fun getNewTracks(): Result<List<Track>>
    suspend fun getAlbum(albumId: String): Result<List<Track>>
    suspend fun getArtist(artistId: String): Result<List<Track>>
}
