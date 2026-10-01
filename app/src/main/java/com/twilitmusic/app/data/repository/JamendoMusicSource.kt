package com.twilitmusic.app.data.repository

import com.twilitmusic.app.data.remote.JamendoApi
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.MusicSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JamendoMusicSource @Inject constructor(
    private val api: JamendoApi
) : MusicSource {

    private val clientId = "56d30c95" // A public free client id from Jamendo documentation

    override suspend fun search(query: String): List<Track> {
        return try {
            val response = api.getTracks(clientId = clientId, search = query)
            response.results.map {
                Track(it.id, it.name, it.artist_name, it.image, it.audio)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getTrack(id: String): Track? {
        return search(id).find { it.id == id }
    }

    override suspend fun getStreamUrl(id: String): String? {
        return getTrack(id)?.sourceUrl
    }

    override suspend fun getFeaturedTracks(): List<Track> {
        return try {
            val response = api.getTracks(clientId = clientId, tags = "pop")
            response.results.map {
                Track(it.id, it.name, it.artist_name, it.image, it.audio)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getNewTracks(): List<Track> {
        return try {
            val response = api.getTracks(clientId = clientId, order = "releasedate_desc")
            response.results.map {
                Track(it.id, it.name, it.artist_name, it.image, it.audio)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
