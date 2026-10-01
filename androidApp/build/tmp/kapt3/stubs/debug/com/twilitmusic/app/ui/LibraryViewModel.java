package com.twilitmusic.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lcom/twilitmusic/app/ui/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "libraryRepository", "Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "playlistDao", "Lcom/twilitmusic/app/data/local/dao/PlaylistDao;", "(Lcom/twilitmusic/app/domain/repository/LibraryRepository;Lcom/twilitmusic/app/data/local/dao/PlaylistDao;)V", "likedTracks", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/twilitmusic/app/domain/model/Track;", "getLikedTracks", "()Lkotlinx/coroutines/flow/StateFlow;", "playHistory", "getPlayHistory", "playlists", "Lcom/twilitmusic/app/data/local/entity/PlaylistEntity;", "getPlaylists", "createPlaylist", "", "name", "", "deletePlaylist", "id", "", "androidApp_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LibraryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.PlaylistDao playlistDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> likedTracks = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> playHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.data.local.entity.PlaylistEntity>> playlists = null;
    
    @javax.inject.Inject()
    public LibraryViewModel(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.PlaylistDao playlistDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> getLikedTracks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> getPlayHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.data.local.entity.PlaylistEntity>> getPlaylists() {
        return null;
    }
    
    public final void createPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void deletePlaylist(long id) {
    }
}