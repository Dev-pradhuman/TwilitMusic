package com.twilitmusic.app.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.twilitmusic.app.data.local.dao.PlayHistoryDao
import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.dao.QueueDao
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaybackStateEntity
import com.twilitmusic.app.data.local.entity.QueueTrackEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class DaoTest {
    private lateinit var db: TwilitDatabase
    private lateinit var queueDao: QueueDao
    private lateinit var playHistoryDao: PlayHistoryDao
    private lateinit var playlistDao: PlaylistDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TwilitDatabase::class.java
        ).allowMainThreadQueries().build()
        queueDao = db.queueDao()
        playHistoryDao = db.playHistoryDao()
        playlistDao = db.playlistDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun queueDao_saveAndRetrieveFullState() = runBlocking {
        val tracks = listOf(QueueTrackEntity(trackId = "1", title = "T1", artist = "A1", artUrl = "U1", sourceUrl = "S1", position = 0))
        val state = PlaybackStateEntity(id = 1, currentIndex = 0, positionMs = 5000L)
        queueDao.saveFullState(tracks, state)
        
        val retrievedTracks = queueDao.getQueue()
        val retrievedState = queueDao.getPlaybackState()
        
        assertEquals(1, retrievedTracks.size)
        assertEquals("1", retrievedTracks[0].trackId)
        assertEquals(5000L, retrievedState?.positionMs)
    }

    @Test
    fun playHistoryDao_insertAndRetrieve() = runBlocking {
        val history = PlayHistoryEntity(trackId = "1", title = "T1", artist = "A1", artUrl = "U1", sourceUrl = "S1", playedAt = 123)
        playHistoryDao.addPlayHistory(history)
        
        val results = playHistoryDao.getPlayHistory(10).first()
        assertEquals(1, results.size)
        assertEquals("T1", results[0].title)
    }

    @Test
    fun playlistDao_createAndRetrieve() = runBlocking {
        val playlist = PlaylistEntity(name = "MyList")
        playlistDao.createPlaylist(playlist)
        
        val results = playlistDao.getAllPlaylists().first()
        assertEquals(1, results.size)
        assertEquals("MyList", results[0].name)
    }
}
