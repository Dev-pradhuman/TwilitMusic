package com.twilitmusic.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.twilitmusic.app.data.local.dao.QueueDao
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

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS `queue_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trackId` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `playback_state` (`id` INTEGER NOT NULL, `currentIndex` INTEGER NOT NULL, `positionMs` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        }
    }

    @Provides
    @Singleton
    fun provideTwilitDatabase(@ApplicationContext context: Context): TwilitDatabase {
        return Room.databaseBuilder(
            context,
            TwilitDatabase::class.java,
            "twilit_music.db"
        )
        .addMigrations(MIGRATION_1_2)
        .build()
    }

    @Provides
    fun provideLikedTrackDao(database: TwilitDatabase): LikedTrackDao = database.likedTrackDao()

    @Provides
    fun providePlaylistDao(database: TwilitDatabase): PlaylistDao = database.playlistDao()

    @Provides
    fun providePlayHistoryDao(database: TwilitDatabase): PlayHistoryDao = database.playHistoryDao()

    @Provides
    fun provideQueueDao(database: TwilitDatabase): QueueDao = database.queueDao()
}