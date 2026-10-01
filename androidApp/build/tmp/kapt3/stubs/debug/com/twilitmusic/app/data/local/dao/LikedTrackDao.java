package com.twilitmusic.app.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\rH\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/twilitmusic/app/data/local/dao/LikedTrackDao;", "", "addLikedTrack", "", "track", "Lcom/twilitmusic/app/data/local/entity/LikedTrackEntity;", "(Lcom/twilitmusic/app/data/local/entity/LikedTrackEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLikedTracks", "Lkotlinx/coroutines/flow/Flow;", "", "isLiked", "", "id", "", "removeLikedTrack", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "androidApp_debug"})
@androidx.room.Dao()
public abstract interface LikedTrackDao {
    
    @androidx.room.Query(value = "SELECT * FROM liked_tracks ORDER BY added_at DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.twilitmusic.app.data.local.entity.LikedTrackEntity>> getLikedTracks();
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM liked_tracks WHERE id = :id)")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLiked(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addLikedTrack(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.entity.LikedTrackEntity track, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM liked_tracks WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeLikedTrack(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}