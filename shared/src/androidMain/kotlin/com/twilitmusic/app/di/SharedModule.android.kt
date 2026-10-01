package com.twilitmusic.app.di

import org.koin.dsl.module
import io.ktor.client.engine.android.Android
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twilitmusic.app.data.local.TwilitDatabase
import android.content.Context
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual val platformModule = module {
    single { Android.create() }
    single<RoomDatabase.Builder<TwilitDatabase>> {
        val context = get<Context>()
        val dbFile = context.getDatabasePath("twilit_music.db")
        Room.databaseBuilder<TwilitDatabase>(
            context = context,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
    }
}
