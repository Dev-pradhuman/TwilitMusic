package com.twilitmusic.app.di

import org.koin.dsl.module
import io.ktor.client.engine.cio.CIO
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.TwilitDatabase
import java.io.File
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import com.twilitmusic.app.playback.AudioPlayer
import com.twilitmusic.app.playback.DesktopAudioPlayer
import com.twilitmusic.app.domain.ConnectivityMonitor
import com.twilitmusic.app.domain.DesktopConnectivityMonitor
import com.twilitmusic.app.domain.TwilitDownloadManager
import com.twilitmusic.app.domain.PlatformPaths
import com.twilitmusic.app.domain.DesktopPlatformPaths
import com.twilitmusic.app.domain.DesktopDownloadManager

actual val platformModule = module {
    single { CIO.create() }
    single<PlatformPaths> { DesktopPlatformPaths() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            name = paths.databasePath
        ).setDriver(BundledSQLiteDriver())
    }
    single<AudioPlayer> { DesktopAudioPlayer() }
    single<ConnectivityMonitor> { DesktopConnectivityMonitor() }
    single<TwilitDownloadManager> { DesktopDownloadManager() }
}
