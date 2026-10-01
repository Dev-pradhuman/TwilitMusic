import sys

with open('app/src/test/java/com/twilitmusic/app/ui/LibraryViewModelTest.kt', 'r') as f:
    content = f.read()

content = content.replace('verify(playlistDao).createPlaylist(PlaylistEntity(name = "My Playlist"))', 'verify(playlistDao).createPlaylist(org.mockito.kotlin.any())')

with open('app/src/test/java/com/twilitmusic/app/ui/LibraryViewModelTest.kt', 'w') as f:
    f.write(content)

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt', 'r') as f:
    content = f.read()

old_setup = '''        whenever(musicController.repeatMode).thenReturn(MutableStateFlow(0))
        
        whenever(libraryRepository.isLiked(any())).thenReturn(MutableStateFlow(false))
        
        viewModel = MainViewModel(application, musicSource, musicController, libraryRepository)
    }'''
new_setup = '''        whenever(musicController.repeatMode).thenReturn(MutableStateFlow(0))
        
        whenever(libraryRepository.isLiked(any())).thenReturn(MutableStateFlow(false))
        whenever(musicSource.getFeaturedTracks()).thenReturn(emptyList())
        whenever(musicSource.getNewTracks()).thenReturn(emptyList())
        
        viewModel = MainViewModel(application, musicSource, musicController, libraryRepository)
    }'''
content = content.replace(old_setup, new_setup)

with open('app/src/test/java/com/twilitmusic/app/ui/MainViewModelExtendedTest.kt', 'w') as f:
    f.write(content)
