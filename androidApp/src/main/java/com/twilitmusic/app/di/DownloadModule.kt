package com.twilitmusic.app.di

import android.content.Context
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.offline.DownloadManager
import com.twilitmusic.app.playback.CacheManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
object DownloadModule {
    @Provides
    @Singleton
    fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager {
        val databaseProvider = StandaloneDatabaseProvider(context)
        val downloadCache = CacheManager.getInstance(context)
        val dataSourceFactory = DefaultDataSource.Factory(context)
        val executor = Executor { it.run() }
        return DownloadManager(context, databaseProvider, downloadCache, dataSourceFactory, executor)
    }
}
