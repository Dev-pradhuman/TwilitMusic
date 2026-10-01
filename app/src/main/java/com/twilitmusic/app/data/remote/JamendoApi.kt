package com.twilitmusic.app.data.remote




interface JamendoApi {
    @retrofit2.http.GET("v3.0/tracks/")
    suspend fun getTracks(
        @retrofit2.http.Query("client_id") clientId: String,
        @retrofit2.http.Query("format") format: String = "json",
        @retrofit2.http.Query("limit") limit: Int = 20,
        @retrofit2.http.Query("order") order: String = "popularity_total",
        @retrofit2.http.Query("tags") tags: String? = null,
        @retrofit2.http.Query("search") search: String? = null
    ): JamendoResponse
}

data class JamendoResponse(
    val results: List<JamendoTrackDto>
)

data class JamendoTrackDto(
    val id: String,
    val name: String,
    val artist_name: String,
    val image: String,
    val audio: String
)
