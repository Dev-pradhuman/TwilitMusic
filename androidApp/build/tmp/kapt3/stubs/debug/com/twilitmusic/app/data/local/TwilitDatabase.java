package com.twilitmusic.app.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/twilitmusic/app/data/local/TwilitDatabase;", "Landroidx/room/RoomDatabase;", "()V", "likedTrackDao", "Lcom/twilitmusic/app/data/local/dao/LikedTrackDao;", "playHistoryDao", "Lcom/twilitmusic/app/data/local/dao/PlayHistoryDao;", "playlistDao", "Lcom/twilitmusic/app/data/local/dao/PlaylistDao;", "queueDao", "Lcom/twilitmusic/app/data/local/dao/QueueDao;", "androidApp_debug"})
@androidx.room.Database(entities = {com.twilitmusic.app.data.local.entity.LikedTrackEntity.class, com.twilitmusic.app.data.local.entity.PlaylistEntity.class, com.twilitmusic.app.data.local.entity.PlaylistTrackEntity.class, com.twilitmusic.app.data.local.entity.PlayHistoryEntity.class, com.twilitmusic.app.data.local.entity.QueueTrackEntity.class, com.twilitmusic.app.data.local.entity.PlaybackStateEntity.class}, version = 2, exportSchema = true)
public abstract class TwilitDatabase extends androidx.room.RoomDatabase {
    
    public TwilitDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.twilitmusic.app.data.local.dao.LikedTrackDao likedTrackDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.twilitmusic.app.data.local.dao.PlaylistDao playlistDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.twilitmusic.app.data.local.dao.PlayHistoryDao playHistoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.twilitmusic.app.data.local.dao.QueueDao queueDao();
}