package com.twilitmusic.app.di

import org.koin.dsl.module
import io.ktor.client.engine.cio.CIO
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.TwilitDatabase
import java.io.File
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual val platformModule = module {
    single { CIO.create() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "TwilitMusic")
            os.contains("mac") -> File(userHome, "Library/Application Support/TwilitMusic")
            else -> File(System.getenv("XDG_DATA_HOME") ?: "$userHome/.local/share", "TwilitMusic")
        }
        if (!appDataDir.exists()) appDataDir.mkdirs()
        val dbFile = File(appDataDir, "twilit_music.db")
        Room.databaseBuilder<TwilitDatabase>(
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
    }
}
