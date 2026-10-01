package com.twilitmusic.app.di

import com.twilitmusic.app.ui.MainViewModel
import com.twilitmusic.app.ui.PlaylistDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::PlaylistDetailViewModel)
}
