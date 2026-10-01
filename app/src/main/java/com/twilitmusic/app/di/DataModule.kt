package com.twilitmusic.app.di

import com.twilitmusic.app.BuildConfig
import com.twilitmusic.app.data.repository.DemoMusicSource
import com.twilitmusic.app.data.repository.JamendoMusicSource
import com.twilitmusic.app.data.repository.LibraryRepositoryImpl
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.domain.repository.MusicSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMusicSource(
        demoMusicSource: DemoMusicSource,
        jamendoMusicSource: JamendoMusicSource
    ): MusicSource {
        // Fallback to DemoMusicSource if key is absent or empty
        return if (BuildConfig.JAMENDO_CLIENT_ID.isEmpty()) {
            demoMusicSource
        } else {
            jamendoMusicSource
        }
    }

    @Provides
    @Singleton
    fun provideLibraryRepository(
        libraryRepositoryImpl: LibraryRepositoryImpl
    ): LibraryRepository = libraryRepositoryImpl
}
