package com.twilitmusic.app.data.repository

import com.twilitmusic.app.data.remote.JamendoApi
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.MusicSource

class JamendoMusicSource (
    private val api: JamendoApi
) : MusicSource {

    private val clientId = "655938da"

    override suspend fun search(query: String): Result<List<Track>> = runCatching {
        val response = api.getTracks(clientId = clientId, search = query)
        response.results.map {
            Track(it.id, it.name, it.artist_name, it.image, it.audio)
        }
    }

    override suspend fun getTrack(id: String): Result<Track?> = runCatching {
        search(id).getOrNull()?.find { it.id == id }
    }

    override suspend fun getStreamUrl(id: String): Result<String?> = runCatching {
        getTrack(id).getOrNull()?.sourceUrl
    }

    override suspend fun getFeaturedTracks(): Result<List<Track>> = runCatching {
        val response = api.getTracks(clientId = clientId, tags = "pop")
        response.results.map {
            Track(it.id, it.name, it.artist_name, it.image, it.audio)
        }
    }

    override suspend fun getNewTracks(): Result<List<Track>> = runCatching {
        val response = api.getTracks(clientId = clientId, order = "releasedate_desc")
        response.results.map {
            Track(it.id, it.name, it.artist_name, it.image, it.audio)
        }
    }

    override suspend fun getAlbum(albumId: String): Result<List<Track>> = runCatching {
        val response = api.getTracks(clientId = clientId, search = albumId) // Simplified API use
        response.results.map {
            Track(it.id, it.name, it.artist_name, it.image, it.audio)
        }
    }

    override suspend fun getArtist(artistId: String): Result<List<Track>> = runCatching {
        val response = api.getTracks(clientId = clientId, search = artistId)
        response.results.map {
            Track(it.id, it.name, it.artist_name, it.image, it.audio)
        }
    }
}
