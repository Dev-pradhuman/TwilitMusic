package com.twilitmusic.app.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u0011\u001a\u00020\n2\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u0007R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0014"}, d2 = {"Lcom/twilitmusic/app/di/DatabaseModule;", "", "()V", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "provideLikedTrackDao", "Lcom/twilitmusic/app/data/local/dao/LikedTrackDao;", "database", "Lcom/twilitmusic/app/data/local/TwilitDatabase;", "providePlayHistoryDao", "Lcom/twilitmusic/app/data/local/dao/PlayHistoryDao;", "providePlaylistDao", "Lcom/twilitmusic/app/data/local/dao/PlaylistDao;", "provideQueueDao", "Lcom/twilitmusic/app/data/local/dao/QueueDao;", "provideTwilitDatabase", "context", "Landroid/content/Context;", "androidApp_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.twilitmusic.app.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.room.migration.Migration getMIGRATION_1_2() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.data.local.TwilitDatabase provideTwilitDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.data.local.dao.LikedTrackDao provideLikedTrackDao(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.TwilitDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.data.local.dao.PlaylistDao providePlaylistDao(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.TwilitDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.data.local.dao.PlayHistoryDao providePlayHistoryDao(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.TwilitDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.data.local.dao.QueueDao provideQueueDao(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.local.TwilitDatabase database) {
        return null;
    }
}