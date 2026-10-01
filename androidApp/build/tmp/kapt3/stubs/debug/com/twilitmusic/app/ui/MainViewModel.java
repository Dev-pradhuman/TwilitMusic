package com.twilitmusic.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'J\u000e\u0010(\u001a\u00020%H\u0082@\u00a2\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,J\u0006\u0010.\u001a\u00020%J\u001c\u0010/\u001a\u00020%2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\'012\u0006\u00102\u001a\u00020,J\u000e\u00103\u001a\u00020%2\u0006\u0010&\u001a\u00020\'J\u000e\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020,J\u0006\u00106\u001a\u00020%J\u0006\u00107\u001a\u00020%J\u0006\u00108\u001a\u00020%R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0019\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001e\u00a8\u00069"}, d2 = {"Lcom/twilitmusic/app/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "musicSource", "Lcom/twilitmusic/app/domain/repository/MusicSource;", "musicController", "Lcom/twilitmusic/app/playback/MusicController;", "context", "Landroid/content/Context;", "downloadManager", "Landroidx/media3/exoplayer/offline/DownloadManager;", "libraryRepository", "Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "queueDao", "Lcom/twilitmusic/app/data/local/dao/QueueDao;", "(Landroid/app/Application;Lcom/twilitmusic/app/domain/repository/MusicSource;Lcom/twilitmusic/app/playback/MusicController;Landroid/content/Context;Landroidx/media3/exoplayer/offline/DownloadManager;Lcom/twilitmusic/app/domain/repository/LibraryRepository;Lcom/twilitmusic/app/data/local/dao/QueueDao;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/twilitmusic/app/ui/MainUiState;", "downloadedTrackIds", "", "", "getDownloadedTrackIds", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "isCurrentTrackLiked", "Lkotlinx/coroutines/flow/StateFlow;", "", "isCurrentTrackLiked$annotations", "()V", "()Lkotlinx/coroutines/flow/StateFlow;", "isOffline", "getMusicController", "()Lcom/twilitmusic/app/playback/MusicController;", "uiState", "getUiState", "downloadTrack", "", "track", "Lcom/twilitmusic/app/domain/model/Track;", "loadHomeData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveTrack", "from", "", "to", "playPause", "playQueue", "tracks", "", "startIndex", "playTrack", "removeTrack", "index", "skipToNext", "skipToPrevious", "toggleLike", "androidApp_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
@androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application application = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.domain.repository.MusicSource musicSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.playback.MusicController musicController = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.media3.exoplayer.offline.DownloadManager downloadManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.QueueDao queueDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.twilitmusic.app.ui.MainUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.ui.MainUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isCurrentTrackLiked = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> isOffline = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> downloadedTrackIds = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.repository.MusicSource musicSource, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.playback.MusicController musicController, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.media3.exoplayer.offline.DownloadManager downloadManager, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.QueueDao queueDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.playback.MusicController getMusicController() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.ui.MainUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isCurrentTrackLiked() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void isCurrentTrackLiked$annotations() {
    }
    
    public final void toggleLike() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> isOffline() {
        return null;
    }
    
    private final java.lang.Object loadHomeData(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void downloadTrack(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> getDownloadedTrackIds() {
        return null;
    }
    
    public final void playTrack(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track) {
    }
    
    public final void playQueue(@org.jetbrains.annotations.NotNull()
    java.util.List<com.twilitmusic.app.domain.model.Track> tracks, int startIndex) {
    }
    
    public final void playPause() {
    }
    
    public final void skipToNext() {
    }
    
    public final void skipToPrevious() {
    }
    
    public final void removeTrack(int index) {
    }
    
    public final void moveTrack(int from, int to) {
    }
}