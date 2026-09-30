package com.twilitmusic.app.di

import com.twilitmusic.app.data.repository.DemoMusicSource
import com.twilitmusic.app.domain.repository.MusicSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindMusicSource(
        demoMusicSource: DemoMusicSource
    ): MusicSource
}
