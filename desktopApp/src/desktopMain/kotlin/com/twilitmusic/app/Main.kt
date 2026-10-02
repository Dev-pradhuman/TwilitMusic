package com.twilitmusic.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.DpSize
import com.twilitmusic.app.ui.TwilitAppScreen
import com.twilitmusic.app.ui.theme.TwilitMusicTheme
import com.twilitmusic.app.di.initKoin
import java.util.Properties
import java.io.File
import java.io.FileInputStream

fun main() {
    // Load Jamendo client ID from env or local.properties
    var clientId = System.getenv("JAMENDO_CLIENT_ID")
    if (clientId.isNullOrEmpty()) {
        val file = File("local.properties")
        if (file.exists()) {
            val props = Properties()
            props.load(FileInputStream(file))
            clientId = props.getProperty("JAMENDO_CLIENT_ID")?.replace("\"", "")
        }
    }
    
    // Pass it to a system property so Ktor/JamendoApi can read it
    System.setProperty("JAMENDO_CLIENT_ID", clientId ?: "")

    initKoin()

    application {
        val state = rememberWindowState(size = DpSize(1024.dp, 768.dp))
        Window(
            onCloseRequest = ::exitApplication,
            title = "TwilitMusic",
            state = state
            // icon = painterResource("icon.png") // We would add an icon here if we had one
        ) {
            window.minimumSize = java.awt.Dimension(800, 600)
            TwilitMusicTheme {
                TwilitAppScreen()
            }
        }
    }
}
