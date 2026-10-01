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

@OptIn(ExperimentalForeignApi::class)
actual val platformModule = module {
    single { Darwin.create() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val dbPath = documentDirectory?.path + "/twilit_music.db"
        Room.databaseBuilder<TwilitDatabase>(
            name = dbPath
        ).setDriver(BundledSQLiteDriver())
    }
}
