package com.twilitmusic.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rH\u0016J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/twilitmusic/app/data/repository/LibraryRepositoryImpl;", "Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "likedTrackDao", "Lcom/twilitmusic/app/data/local/dao/LikedTrackDao;", "playHistoryDao", "Lcom/twilitmusic/app/data/local/dao/PlayHistoryDao;", "(Lcom/twilitmusic/app/data/local/dao/LikedTrackDao;Lcom/twilitmusic/app/data/local/dao/PlayHistoryDao;)V", "addPlayHistory", "", "track", "Lcom/twilitmusic/app/domain/model/Track;", "(Lcom/twilitmusic/app/domain/model/Track;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLikedTracks", "Lkotlinx/coroutines/flow/Flow;", "", "getPlayHistory", "isLiked", "", "trackId", "", "toggleLike", "(Lcom/twilitmusic/app/domain/model/Track;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "androidApp_debug"})
public final class LibraryRepositoryImpl implements com.twilitmusic.app.domain.repository.LibraryRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.LikedTrackDao likedTrackDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.twilitmusic.app.data.local.dao.PlayHistoryDao playHistoryDao = null;
    
    @javax.inject.Inject()
    public LibraryRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.LikedTrackDao likedTrackDao, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.dao.PlayHistoryDao playHistoryDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.twilitmusic.app.domain.model.Track>> getLikedTracks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLiked(@org.jetbrains.annotations.NotNull()
    java.lang.String trackId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object toggleLike(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, boolean isLiked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.twilitmusic.app.domain.model.Track>> getPlayHistory() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addPlayHistory(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}