package com.twilitmusic.app.playback;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020/H\u0086@\u00a2\u0006\u0002\u00101J\u0016\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u0015J\u0006\u00105\u001a\u00020/J\u001e\u00106\u001a\u00020/2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\b\u0002\u00108\u001a\u00020\u0015J\u000e\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020\rJ\u000e\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020\u0015J\b\u0010=\u001a\u00020/H\u0002J\u000e\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u000bJ\u001e\u0010@\u001a\u00020/2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\b\u0002\u00108\u001a\u00020\u0015J\u0006\u0010A\u001a\u00020/J\u0006\u0010B\u001a\u00020/J\u0006\u0010C\u001a\u00020/J\u0012\u0010D\u001a\u00020/2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002J\b\u0010G\u001a\u00020/H\u0003R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001a\u00a8\u0006H"}, d2 = {"Lcom/twilitmusic/app/playback/MusicController;", "", "context", "Landroid/content/Context;", "libraryRepository", "Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "queueDao", "Lcom/twilitmusic/app/data/local/dao/QueueDao;", "(Landroid/content/Context;Lcom/twilitmusic/app/domain/repository/LibraryRepository;Lcom/twilitmusic/app/data/local/dao/QueueDao;)V", "_bufferedPosition", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentTrack", "Lcom/twilitmusic/app/domain/model/Track;", "_duration", "_isPlaying", "", "_position", "_queue", "", "_repeatMode", "", "_shuffleModeEnabled", "bufferedPosition", "Lkotlinx/coroutines/flow/StateFlow;", "getBufferedPosition", "()Lkotlinx/coroutines/flow/StateFlow;", "currentTrack", "getCurrentTrack", "duration", "getDuration", "isPlaying", "mediaController", "Landroidx/media3/session/MediaController;", "getMediaController$androidApp_debug", "()Landroidx/media3/session/MediaController;", "setMediaController$androidApp_debug", "(Landroidx/media3/session/MediaController;)V", "position", "getPosition", "queue", "getQueue", "repeatMode", "getRepeatMode", "shuffleModeEnabled", "getShuffleModeEnabled", "cycleRepeatMode", "", "init", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveTrack", "fromIndex", "toIndex", "playPause", "playQueue", "tracks", "startIndex", "playTrack", "track", "removeTrack", "index", "saveQueueState", "seekTo", "positionMs", "setQueueWithoutPlaying", "skipToNext", "skipToPrevious", "toggleShuffle", "updateCurrentTrack", "mediaItem", "Landroidx/media3/common/MediaItem;", "updateQueue", "androidApp_debug"})
public final class MusicController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.QueueDao queueDao = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.media3.session.MediaController mediaController;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPlaying = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPlaying = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.twilitmusic.app.domain.model.Track> _currentTrack = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.domain.model.Track> currentTrack = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> _queue = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> queue = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _position = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> position = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _duration = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> duration = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _bufferedPosition = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> bufferedPosition = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _shuffleModeEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> shuffleModeEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _repeatMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> repeatMode = null;
    
    @javax.inject.Inject()
    public MusicController(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.repository.LibraryRepository libraryRepository, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.QueueDao queueDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.media3.session.MediaController getMediaController$androidApp_debug() {
        return null;
    }
    
    public final void setMediaController$androidApp_debug(@org.jetbrains.annotations.Nullable()
    androidx.media3.session.MediaController p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPlaying() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.twilitmusic.app.domain.model.Track> getCurrentTrack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.twilitmusic.app.domain.model.Track>> getQueue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getBufferedPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShuffleModeEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getRepeatMode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object init(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void updateCurrentTrack(androidx.media3.common.MediaItem mediaItem) {
    }
    
    @androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
    private final void updateQueue() {
    }
    
    private final void saveQueueState() {
    }
    
    public final void playTrack(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track) {
    }
    
    public final void playQueue(@org.jetbrains.annotations.NotNull()
    java.util.List<com.twilitmusic.app.domain.model.Track> tracks, int startIndex) {
    }
    
    public final void setQueueWithoutPlaying(@org.jetbrains.annotations.NotNull()
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
    
    public final void moveTrack(int fromIndex, int toIndex) {
    }
    
    public final void seekTo(long positionMs) {
    }
    
    public final void toggleShuffle() {
    }
    
    public final void cycleRepeatMode() {
    }
}