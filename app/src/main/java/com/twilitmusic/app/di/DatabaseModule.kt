package com.twilitmusic.app.di

import android.content.Context
import androidx.room.Room
import com.twilitmusic.app.data.local.TwilitDatabase
import com.twilitmusic.app.data.local.dao.LikedTrackDao
import com.twilitmusic.app.data.local.dao.PlayHistoryDao
import com.twilitmusic.app.data.local.dao.PlaylistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTwilitDatabase(@ApplicationContext context: Context): TwilitDatabase {
        return Room.databaseBuilder(
            context,
            TwilitDatabase::class.java,
            "twilit_music.db"
        ).build()
    }

    @Provides
    fun provideLikedTrackDao(database: TwilitDatabase): LikedTrackDao = database.likedTrackDao()

    @Provides
    fun providePlaylistDao(database: TwilitDatabase): PlaylistDao = database.playlistDao()

    @Provides
    fun providePlayHistoryDao(database: TwilitDatabase): PlayHistoryDao = database.playHistoryDao()
}
