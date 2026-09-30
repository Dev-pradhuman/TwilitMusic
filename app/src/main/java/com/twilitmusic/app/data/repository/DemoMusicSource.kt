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

    override suspend fun search(query: String): List<Track> {
        return tracks.filter { it.title.contains(query, ignoreCase = true) }
    }

    override suspend fun getTrack(id: String): Track? {
        return tracks.find { it.id == id }
    }

    override suspend fun getStreamUrl(id: String): String? {
        return getTrack(id)?.sourceUrl
    }

    override suspend fun getFeaturedTracks(): List<Track> {
        return tracks.take(10)
    }

    override suspend fun getNewTracks(): List<Track> {
        return tracks.drop(10)
    }
}
