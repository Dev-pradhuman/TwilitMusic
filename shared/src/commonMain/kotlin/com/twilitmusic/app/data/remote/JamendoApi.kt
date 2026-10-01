package com.twilitmusic.app.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

class JamendoApi(private val client: HttpClient) {
    suspend fun getTracks(
        clientId: String,
        format: String = "json",
        limit: Int = 20,
        order: String = "popularity_total",
        tags: String? = null,
        search: String? = null
    ): JamendoResponse {
        return client.get("https://api.jamendo.com/v3.0/tracks/") {
            url {
                parameters.append("client_id", clientId)
                parameters.append("format", format)
                parameters.append("limit", limit.toString())
                parameters.append("order", order)
                if (tags != null) parameters.append("tags", tags)
                if (search != null) parameters.append("search", search)
            }
        }.body()
    }
}

@Serializable
data class JamendoResponse(
    val results: List<JamendoTrackDto>
)

@Serializable
data class JamendoTrackDto(
    val id: String,
    val name: String,
    val artist_name: String,
    val image: String,
    val audio: String
)
