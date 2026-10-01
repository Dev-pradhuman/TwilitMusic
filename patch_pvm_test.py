import sys

with open('app/src/test/java/com/twilitmusic/app/ui/PlaylistDetailViewModelTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import kotlinx.coroutines.flow.first', 'import kotlinx.coroutines.flow.first\nimport kotlinx.coroutines.launch\nimport kotlinx.coroutines.test.advanceUntilIdle')

old_playlist_loads = '''    @Test
    fun `playlist loads correctly`() = runTest(testDispatcher) {
        val playlist = viewModel.playlist.first()
        assertEquals("My Playlist", playlist?.name)
    }'''
new_playlist_loads = '''    @Test
    fun `playlist loads correctly`() = runTest(testDispatcher) {
        val job = launch { viewModel.playlist.collect {} }
        advanceUntilIdle()
        assertEquals("My Playlist", viewModel.playlist.value?.name)
        job.cancel()
    }'''
content = content.replace(old_playlist_loads, new_playlist_loads)

old_tracks_loads = '''    @Test
    fun `tracks load correctly`() = runTest(testDispatcher) {
        val tracks = viewModel.tracks.first()
        assertEquals(1, tracks.size)
        assertEquals("T1", tracks[0].trackId)
    }'''
new_tracks_loads = '''    @Test
    fun `tracks load correctly`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        assertEquals(1, viewModel.tracks.value.size)
        assertEquals("T1", viewModel.tracks.value[0].trackId)
        job.cancel()
    }'''
content = content.replace(old_tracks_loads, new_tracks_loads)

old_rename = '''    @Test
    fun `rename playlist calls dao`() = runTest(testDispatcher) {
        viewModel.renamePlaylist("New Name")
        verify(playlistDao).createPlaylist(any())
    }'''
new_rename = '''    @Test
    fun `rename playlist calls dao`() = runTest(testDispatcher) {
        val job = launch { viewModel.playlist.collect {} }
        advanceUntilIdle()
        viewModel.renamePlaylist("New Name")
        advanceUntilIdle()
        verify(playlistDao).createPlaylist(any())
        job.cancel()
    }'''
content = content.replace(old_rename, new_rename)

old_remove = '''    @Test
    fun `remove track calls dao`() = runTest(testDispatcher) {
        viewModel.removeTrack(1L)
        verify(playlistDao).removeTrackFromPlaylist(1L)
    }'''
new_remove = '''    @Test
    fun `remove track calls dao`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        viewModel.removeTrack(1L)
        advanceUntilIdle()
        verify(playlistDao).removeTrackFromPlaylist(1L)
        job.cancel()
    }'''
content = content.replace(old_remove, new_remove)

old_move = '''    @Test
    fun `move track updates order`() = runTest(testDispatcher) {
        viewModel.moveTrack(0, 0)
        verify(playlistDao).addTrackToPlaylist(any())
    }'''
new_move = '''    @Test
    fun `move track updates order`() = runTest(testDispatcher) {
        val job = launch { viewModel.tracks.collect {} }
        advanceUntilIdle()
        viewModel.moveTrack(0, 0)
        advanceUntilIdle()
        verify(playlistDao).addTrackToPlaylist(any())
        job.cancel()
    }'''
content = content.replace(old_move, new_move)

with open('app/src/test/java/com/twilitmusic/app/ui/PlaylistDetailViewModelTest.kt', 'w') as f:
    f.write(content)
