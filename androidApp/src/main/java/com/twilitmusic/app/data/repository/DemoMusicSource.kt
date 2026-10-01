package com.twilitmusic.app.data.repository

import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.MusicSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemoMusicSource @Inject constructor() : MusicSource {

    private val tracks = (1..20).map { i ->
        Track(
            id = "track_$i",
            title = "Demo Song $i",
            artist = "SoundHelix",
            artUrl = "https://picsum.photos/seed/track$i/300/300",
            sourceUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-${(i % 17) + 1}.mp3"
        )
    }

    override suspend fun search(query: String): Result<List<Track>> = runCatching {
        tracks.filter { it.title.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true) }
    }

    override suspend fun getTrack(id: String): Result<Track?> = runCatching {
        tracks.find { it.id == id }
    }

    override suspend fun getStreamUrl(id: String): Result<String?> = runCatching {
        getTrack(id).getOrNull()?.sourceUrl
    }

    override suspend fun getFeaturedTracks(): Result<List<Track>> = runCatching {
        tracks.take(5)
    }

    override suspend fun getNewTracks(): Result<List<Track>> = runCatching {
        tracks.drop(5).take(5)
    }

    override suspend fun getAlbum(albumId: String): Result<List<Track>> = runCatching {
        tracks.filter { it.title.contains(albumId, true) }
    }

    override suspend fun getArtist(artistId: String): Result<List<Track>> = runCatching {
        tracks.filter { it.artist.contains(artistId, true) }
    }
}
