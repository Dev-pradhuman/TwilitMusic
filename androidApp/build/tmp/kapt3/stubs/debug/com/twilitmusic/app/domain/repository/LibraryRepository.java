package com.twilitmusic.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\r\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "", "addPlayHistory", "", "track", "Lcom/twilitmusic/app/domain/model/Track;", "(Lcom/twilitmusic/app/domain/model/Track;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLikedTracks", "Lkotlinx/coroutines/flow/Flow;", "", "getPlayHistory", "isLiked", "", "trackId", "", "toggleLike", "(Lcom/twilitmusic/app/domain/model/Track;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "androidApp_debug"})
public abstract interface LibraryRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.twilitmusic.app.domain.model.Track>> getLikedTracks();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLiked(@org.jetbrains.annotations.NotNull()
    java.lang.String trackId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object toggleLike(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, boolean isLiked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.twilitmusic.app.domain.model.Track>> getPlayHistory();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addPlayHistory(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}