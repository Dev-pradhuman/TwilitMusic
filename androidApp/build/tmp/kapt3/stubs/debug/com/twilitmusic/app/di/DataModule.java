package com.twilitmusic.app.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u00a8\u0006\r"}, d2 = {"Lcom/twilitmusic/app/di/DataModule;", "", "()V", "provideLibraryRepository", "Lcom/twilitmusic/app/domain/repository/LibraryRepository;", "libraryRepositoryImpl", "Lcom/twilitmusic/app/data/repository/LibraryRepositoryImpl;", "provideMusicSource", "Lcom/twilitmusic/app/domain/repository/MusicSource;", "demoMusicSource", "Lcom/twilitmusic/app/data/repository/DemoMusicSource;", "jamendoMusicSource", "Lcom/twilitmusic/app/data/repository/JamendoMusicSource;", "androidApp_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DataModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.twilitmusic.app.di.DataModule INSTANCE = null;
    
    private DataModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.domain.repository.MusicSource provideMusicSource(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.repository.DemoMusicSource demoMusicSource, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.repository.JamendoMusicSource jamendoMusicSource) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.twilitmusic.app.domain.repository.LibraryRepository provideLibraryRepository(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.data.repository.LibraryRepositoryImpl libraryRepositoryImpl) {
        return null;
    }
}