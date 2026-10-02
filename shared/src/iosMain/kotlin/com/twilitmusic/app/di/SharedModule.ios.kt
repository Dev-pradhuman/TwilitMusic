package com.twilitmusic.app.di

import org.koin.dsl.module
import io.ktor.client.engine.darwin.Darwin
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.TwilitDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import com.twilitmusic.app.playback.AudioPlayer
import com.twilitmusic.app.playback.IosAudioPlayer
import com.twilitmusic.app.domain.ConnectivityMonitor
import com.twilitmusic.app.domain.IosConnectivityMonitor
import com.twilitmusic.app.domain.TwilitDownloadManager
import com.twilitmusic.app.domain.PlatformPaths
import com.twilitmusic.app.domain.IosPlatformPaths
import com.twilitmusic.app.domain.IosDownloadManager

@OptIn(ExperimentalForeignApi::class)
actual val platformModule = module {
    single { Darwin.create() }
    single<PlatformPaths> { IosPlatformPaths() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            name = paths.databasePath
        ).setDriver(BundledSQLiteDriver())
    }
    single<AudioPlayer> { IosAudioPlayer() }
    single<ConnectivityMonitor> { IosConnectivityMonitor() }
    single<TwilitDownloadManager> { IosDownloadManager() }
}
