package com.twilitmusic.app.di

import com.twilitmusic.app.data.local.TwilitDatabase
import com.twilitmusic.app.data.remote.JamendoApi
import com.twilitmusic.app.data.repository.DemoMusicSource
import com.twilitmusic.app.data.repository.JamendoMusicSource
import com.twilitmusic.app.data.repository.LibraryRepositoryImpl
import com.twilitmusic.app.domain.repository.LibraryRepository
import com.twilitmusic.app.domain.repository.MusicSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import androidx.room.RoomDatabase

val coreModule = module {
    // Ktor HttpClient
    single {
        HttpClient(get<HttpClientEngine>()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }
    
    // Database
    single { 
        get<RoomDatabase.Builder<TwilitDatabase>>()
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<TwilitDatabase>().likedTrackDao() }
    single { get<TwilitDatabase>().playlistDao() }
    single { get<TwilitDatabase>().playHistoryDao() }
    single { get<TwilitDatabase>().queueDao() }

    // APIs and Repositories
    singleOf(::JamendoApi)
    singleOf(::JamendoMusicSource) bind MusicSource::class
    singleOf(::LibraryRepositoryImpl) bind LibraryRepository::class
}

fun initKoin(appDeclaration: org.koin.dsl.KoinAppDeclaration = {}) {
    org.koin.core.context.startKoin {
        appDeclaration()
        modules(platformModule, coreModule, uiModule)
    }
}
