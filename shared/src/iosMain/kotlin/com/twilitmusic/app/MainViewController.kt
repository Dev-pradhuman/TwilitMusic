package com.twilitmusic.app

import androidx.compose.ui.window.ComposeUIViewController
import com.twilitmusic.app.ui.TwilitAppScreen
import com.twilitmusic.app.ui.theme.TwilitMusicTheme
import com.twilitmusic.app.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        TwilitMusicTheme {
            TwilitAppScreen()
        }
    }
}
