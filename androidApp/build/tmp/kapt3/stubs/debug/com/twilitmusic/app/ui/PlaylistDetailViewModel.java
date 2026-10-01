package com.twilitmusic.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bR\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\u001c"}, d2 = {"Lcom/twilitmusic/app/ui/PlaylistDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "playlistDao", "Lcom/twilitmusic/app/data/local/dao/PlaylistDao;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/twilitmusic/app/data/local/dao/PlaylistDao;)V", "playlist", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/twilitmusic/app/data/local/entity/PlaylistEntity;", "getPlaylist", "()Lkotlinx/coroutines/flow/StateFlow;", "playlistId", "", "tracks", "", "Lcom/twilitmusic/app/data/local/entity/PlaylistTrackEntity;", "getTracks", "moveTrack", "", "fromIndex", "", "toIndex", "removeTrack", "playlistTrackId", "renamePlaylist", "newName", "", "androidApp_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PlaylistDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.PlaylistDao playlistDao = null;
    private final long playlistId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.data.local.entity.PlaylistEntity> playlist = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.data.local.entity.PlaylistTrackEntity>> tracks = null;
    
    @javax.inject.Inject()
    public PlaylistDetailViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.PlaylistDao playlistDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.data.local.entity.PlaylistEntity> getPlaylist() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.data.local.entity.PlaylistTrackEntity>> getTracks() {
        return null;
    }
    
    public final void renamePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String newName) {
    }
    
    public final void removeTrack(long playlistTrackId) {
    }
    
    public final void moveTrack(int fromIndex, int toIndex) {
    }
}