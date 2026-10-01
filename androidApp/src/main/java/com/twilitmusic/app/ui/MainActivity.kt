package com.twilitmusic.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.twilitmusic.app.ui.theme.TwilitMusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwilitMusicTheme {
                TwilitAppScreen()
            }
        }
    }
}
