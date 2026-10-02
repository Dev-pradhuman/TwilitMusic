import os
import glob

def fake_library_repo():
    return """
class FakeLibraryRepository : LibraryRepository {
    var likedTracks = MutableStateFlow<List<Track>>(emptyList())
    var playHistory = MutableStateFlow<List<Track>>(emptyList())
    var downloadedTracks = MutableStateFlow<List<Track>>(emptyList())
    var didLike = false
    
    override fun getLikedTracks(): Flow<List<Track>> = likedTracks
    override fun getPlayHistory(): Flow<List<Track>> = playHistory
    override fun getDownloadedTracks(): Flow<List<Track>> = downloadedTracks
    
    override suspend fun setTrackLiked(track: Track, isLiked: Boolean) {
        didLike = isLiked
    }
    
    override suspend fun addToHistory(track: Track) {}
    override suspend fun addDownloadedTrack(track: Track) {}
    override suspend fun removeDownloadedTrack(trackId: String) {}
    override suspend fun clearHistory() {}
}
"""

def fake_playlist_dao():
    return """
class FakePlaylistDao : PlaylistDao {
    var playlists = MutableStateFlow<List<PlaylistEntity>>(emptyList())
    var created = mutableListOf<PlaylistEntity>()
    var deleted = mutableListOf<Long>()
    
    override fun getAllPlaylists(): Flow<List<PlaylistEntity>> = playlists
    override fun getPlaylistTracks(playlistId: Long): Flow<List<PlaylistTrackEntity>> = MutableStateFlow(emptyList())
    override suspend fun createPlaylist(playlist: PlaylistEntity): Long {
        created.add(playlist)
        return 1L
    }
    override suspend fun deletePlaylist(playlistId: Long) {
        deleted.add(playlistId)
    }
    override suspend fun addTrackToPlaylist(track: PlaylistTrackEntity) {}
    override suspend fun removeTrackFromPlaylist(playlistId: Long, trackId: String) {}
}
"""

def fake_audio_player():
    return """
class FakeAudioPlayer : AudioPlayer {
    override val isPlaying = MutableStateFlow(false)
    override val currentTrack = MutableStateFlow<Track?>(null)
    override val currentPosition = MutableStateFlow(0L)
    override val duration = MutableStateFlow(0L)
    override val queue = MutableStateFlow<List<Track>>(emptyList())
    override val repeatMode = MutableStateFlow(0)
    override val shuffleMode = MutableStateFlow(false)
    
    var played = false
    var paused = false
    
    override fun play() { played = true; isPlaying.value = true }
    override fun pause() { paused = true; isPlaying.value = false }
    override fun stop() { isPlaying.value = false }
    override fun seekTo(positionMs: Long) { currentPosition.value = positionMs }
    override fun skipToNext() {}
    override fun skipToPrevious() {}
    override fun setQueue(tracks: List<Track>, startIndex: Int) { queue.value = tracks }
    override fun addTrackToQueue(track: Track) { queue.value = queue.value + track }
    override fun removeTrackFromQueue(index: Int) {}
    override fun moveTrackInQueue(fromIndex: Int, toIndex: Int) {}
    override fun setRepeatMode(mode: Int) { repeatMode.value = mode }
    override fun toggleShuffle() { shuffleMode.value = !shuffleMode.value }
    override fun setShuffleModeEnabled(enabled: Boolean) { shuffleMode.value = enabled }
    override fun release() {}
}
"""

# Let's fix LibraryViewModelTest
lib_test_path = "shared/src/commonTest/kotlin/com/twilitmusic/app/ui/LibraryViewModelTest.kt"
with open(lib_test_path, 'w') as f:
    f.write(f"""package com.twilitmusic.app.ui

import com.twilitmusic.app.data.local.dao.PlaylistDao
import com.twilitmusic.app.data.local.entity.PlaylistEntity
import com.twilitmusic.app.data.local.entity.PlaylistTrackEntity
import com.twilitmusic.app.domain.model.Track
import com.twilitmusic.app.domain.repository.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

{fake_library_repo()}
{fake_playlist_dao()}

@OptIn(ExperimentalCoroutinesApi::class)
class LibraryViewModelTest {{

    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var libraryRepository: FakeLibraryRepository
    private lateinit var playlistDao: FakePlaylistDao
    private lateinit var viewModel: LibraryViewModel

    @BeforeTest
    fun setup() {{
        Dispatchers.setMain(testDispatcher)
        libraryRepository = FakeLibraryRepository()
        playlistDao = FakePlaylistDao()
        viewModel = LibraryViewModel(libraryRepository, playlistDao)
    }}

    @AfterTest
    fun tearDown() {{
        Dispatchers.resetMain()
    }}

    @Test
    fun `createPlaylist calls dao`() = runTest {{
        viewModel.createPlaylist("My Playlist")
        testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(playlistDao.created.isNotEmpty())
    }}
    
    @Test
    fun `deletePlaylist calls dao`() = runTest {{
        viewModel.deletePlaylist(1L)
        testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(playlistDao.deleted.contains(1L))
    }}
}}
""")

