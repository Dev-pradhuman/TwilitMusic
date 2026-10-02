package com.twilitmusic.app.di

import org.koin.dsl.module
import com.twilitmusic.app.domain.PlatformPaths
import com.twilitmusic.app.domain.AndroidPlatformPaths
import com.twilitmusic.app.domain.ConnectivityMonitor
import com.twilitmusic.app.domain.AndroidConnectivityMonitor
import com.twilitmusic.app.domain.TwilitDownloadManager
import com.twilitmusic.app.domain.AndroidDownloadManager
import org.koin.android.ext.koin.androidContext
import io.ktor.client.engine.android.Android
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.TwilitDatabase
import android.content.Context
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual val platformModule = module {
    single { Android.create() }
    single<PlatformPaths> { AndroidPlatformPaths(androidContext()) }
    single<ConnectivityMonitor> { AndroidConnectivityMonitor(androidContext()) }
    single<TwilitDownloadManager> { AndroidDownloadManager(androidContext()) }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val paths = get<PlatformPaths>()
        Room.databaseBuilder<TwilitDatabase>(
            context = androidContext(),
            name = paths.databasePath
        ).setDriver(BundledSQLiteDriver())
    }
}
