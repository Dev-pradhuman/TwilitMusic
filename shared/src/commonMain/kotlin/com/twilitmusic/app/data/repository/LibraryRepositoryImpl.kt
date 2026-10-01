package com.twilitmusic.app.data.repository

import com.twilitmusic.app.data.local.dao.LikedTrackDao
import com.twilitmusic.app.data.local.dao.PlayHistoryDao
import com.twilitmusic.app.data.local.entity.LikedTrackEntity
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibraryRepositoryImpl (
    private val likedTrackDao: LikedTrackDao,
    private val playHistoryDao: PlayHistoryDao
) : LibraryRepository {

    override fun getLikedTracks(): Flow<List<Track>> = likedTrackDao.getLikedTracks().map { entities ->
        entities.map { Track(it.id, it.title, it.artist, it.artUrl, it.sourceUrl) }
    }

    override fun isLiked(trackId: String): Flow<Boolean> = likedTrackDao.isLiked(trackId)

    override suspend fun toggleLike(track: Track, isLiked: Boolean) {
        if (isLiked) {
            likedTrackDao.addLikedTrack(
                LikedTrackEntity(track.id, track.title, track.artist, track.artUrl, track.sourceUrl)
            )
        } else {
            likedTrackDao.removeLikedTrack(track.id)
        }
    }

    override fun getPlayHistory(): Flow<List<Track>> = playHistoryDao.getPlayHistory().map { entities ->
        entities.map { Track(it.trackId, it.title, it.artist, it.artUrl, it.sourceUrl) }
    }

    override suspend fun addPlayHistory(track: Track) {
        playHistoryDao.addPlayHistory(
            PlayHistoryEntity(
                trackId = track.id,
                title = track.title,
                artist = track.artist,
                artUrl = track.artUrl,
                sourceUrl = track.sourceUrl
            )
        )
    }
}
