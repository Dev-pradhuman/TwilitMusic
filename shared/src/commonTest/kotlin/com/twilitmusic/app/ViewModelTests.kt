package com.twilitmusic.app

import com.twilitmusic.app.ui.LibraryViewModel
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.AfterTest

class FakeLibraryRepository : LibraryRepository {
    var likedTracks = MutableStateFlow<List<Track>>(emptyList())
    var playHistory = MutableStateFlow<List<Track>>(emptyList())
    
    override fun getLikedTracks(): Flow<List<Track>> = likedTracks
    override fun isLiked(trackId: String): Flow<Boolean> = MutableStateFlow(false)
    override suspend fun toggleLike(track: Track, isLiked: Boolean) {}
    override fun getPlayHistory(): Flow<List<Track>> = playHistory
    override suspend fun addPlayHistory(track: Track) {}
}

class FakePlaylistDao : PlaylistDao {
    var created = mutableListOf<PlaylistEntity>()
    var deleted = mutableListOf<Long>()
    
    override fun getAllPlaylists(): Flow<List<PlaylistEntity>> = MutableStateFlow(emptyList())
    override suspend fun createPlaylist(playlist: PlaylistEntity): Long {
        created.add(playlist)
        return 1L
    }
    override suspend fun deletePlaylist(playlistId: Long) {
        deleted.add(playlistId)
    }
    override fun getTracksForPlaylist(playlistId: Long): Flow<List<PlaylistTrackEntity>> = MutableStateFlow(emptyList())
    override suspend fun addTrackToPlaylist(track: PlaylistTrackEntity) {}
    override suspend fun removeTrackFromPlaylist(playlistTrackId: Long) {}
}

class ViewModelTests {
    private val testDispatcher = StandardTestDispatcher()
    
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLibraryViewModel() = runTest {
        val repo = FakeLibraryRepository()
        val dao = FakePlaylistDao()
        val viewModel = LibraryViewModel(repo, dao)
        
        viewModel.createPlaylist("Test Playlist")
        testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(dao.created.isNotEmpty())
    }
}
