package com.twilitmusic.app.di

import com.twilitmusic.app.playback.AudioPlayer
import com.twilitmusic.app.playback.MusicController
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { MusicController(androidContext(), get(), get()) } bind AudioPlayer::class
}
