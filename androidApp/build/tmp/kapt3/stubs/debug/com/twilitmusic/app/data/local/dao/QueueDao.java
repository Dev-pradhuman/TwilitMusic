package com.twilitmusic.app.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0097@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/twilitmusic/app/data/local/dao/QueueDao;", "", "clearQueue", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlaybackState", "Lcom/twilitmusic/app/data/local/entity/PlaybackStateEntity;", "getQueue", "", "Lcom/twilitmusic/app/data/local/entity/QueueTrackEntity;", "insertQueue", "tracks", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveFullState", "state", "(Ljava/util/List;Lcom/twilitmusic/app/data/local/entity/PlaybackStateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePlaybackState", "(Lcom/twilitmusic/app/data/local/entity/PlaybackStateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "androidApp_debug"})
@androidx.room.Dao()
public abstract interface QueueDao {
    
    @androidx.room.Query(value = "SELECT * FROM queue_tracks ORDER BY position ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getQueue(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.twilitmusic.app.data.local.entity.QueueTrackEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM playback_state WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlaybackState(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.twilitmusic.app.data.local.entity.PlaybackStateEntity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM queue_tracks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearQueue(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertQueue(@org.jetbrains.annotations.NotNull()
    java.util.List<com.twilitmusic.app.data.local.entity.QueueTrackEntity> tracks, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object savePlaybackState(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.entity.PlaybackStateEntity state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Transaction()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveFullState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.twilitmusic.app.data.local.entity.QueueTrackEntity> tracks, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.entity.PlaybackStateEntity state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @androidx.room.Transaction()
        @org.jetbrains.annotations.Nullable()
        public static java.lang.Object saveFullState(@org.jetbrains.annotations.NotNull()
        com.twilitmusic.app.data.local.dao.QueueDao $this, @org.jetbrains.annotations.NotNull()
        java.util.List<com.twilitmusic.app.data.local.entity.QueueTrackEntity> tracks, @org.jetbrains.annotations.NotNull()
        com.twilitmusic.app.data.local.entity.PlaybackStateEntity state, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
    }
}