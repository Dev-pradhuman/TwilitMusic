package com.twilitmusic.app.domain
import java.io.File

class DesktopPlatformPaths : PlatformPaths {
    override val appDataDir: String
        get() {
            val os = System.getProperty("os.name").lowercase()
            val userHome = System.getProperty("user.home")
            val dir = when {
                os.contains("win") -> File(System.getenv("APPDATA"), "TwilitMusic")
                os.contains("mac") -> File(userHome, "Library/Application Support/TwilitMusic")
                else -> File(System.getenv("XDG_DATA_HOME") ?: "$userHome/.local/share", "TwilitMusic")
            }
            if (!dir.exists()) dir.mkdirs()
            return dir.absolutePath
        }

    override val databasePath: String
        get() = File(appDataDir, "twilit_music.db").absolutePath
}
