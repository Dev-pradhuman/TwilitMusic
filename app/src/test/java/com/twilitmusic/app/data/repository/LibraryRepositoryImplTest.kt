package com.twilitmusic.app.data.repository

import com.twilitmusic.app.data.local.dao.LikedTrackDao
import com.twilitmusic.app.data.local.dao.PlayHistoryDao
import com.twilitmusic.app.data.local.entity.LikedTrackEntity
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity
import com.twilitmusic.app.domain.model.Track
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LibraryRepositoryImplTest {

    private lateinit var likedTrackDao: LikedTrackDao
    private lateinit var playHistoryDao: PlayHistoryDao
    private lateinit var repository: LibraryRepositoryImpl

    private val sampleTrack = Track("1", "T1", "A1", "U1", "S1")

    @Before
    fun setup() {
        likedTrackDao = mock()
        playHistoryDao = mock()
        repository = LibraryRepositoryImpl(likedTrackDao, playHistoryDao)
    }

    @Test
    fun `isLiked delegates to dao`() = runTest {
        whenever(likedTrackDao.isLiked("1")).thenReturn(flowOf(true))
        val result = repository.isLiked("1").first()
        assertTrue(result)
    }

    @Test
    fun `toggleLike true adds to dao`() = runTest {
        repository.toggleLike(sampleTrack, true)
        verify(likedTrackDao).addLikedTrack(any())
    }

    @Test
    fun `toggleLike false removes from dao`() = runTest {
        repository.toggleLike(sampleTrack, false)
        verify(likedTrackDao).removeLikedTrack("1")
    }

    @Test
    fun `getLikedTracks maps entities to models`() = runTest {
        val entities = listOf(LikedTrackEntity("1", "T1", "A1", "U1", "S1", 123))
        whenever(likedTrackDao.getLikedTracks()).thenReturn(flowOf(entities))
        
        val models = repository.getLikedTracks().first()
        assertEquals(1, models.size)
        assertEquals("1", models[0].id)
    }

    @Test
    fun `getPlayHistory maps entities to models`() = runTest {
        val entities = listOf(PlayHistoryEntity(id = 0L, trackId = "1", title = "T1", artist = "A1", artUrl = "U1", sourceUrl = "S1", playedAt = 123L))
        whenever(playHistoryDao.getPlayHistory(20)).thenReturn(flowOf(entities))
        
        val models = repository.getPlayHistory().first()
        assertEquals(1, models.size)
        assertEquals("1", models[0].id)
    }

    @Test
    fun `addToHistory calls dao`() = runTest {
        repository.addPlayHistory(sampleTrack)
        verify(playHistoryDao).addPlayHistory(any())
    }
}
