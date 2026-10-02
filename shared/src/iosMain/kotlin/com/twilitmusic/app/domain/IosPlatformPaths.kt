package com.twilitmusic.app.domain

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class IosPlatformPaths : PlatformPaths {
    @OptIn(ExperimentalForeignApi::class)
    override val appDataDir: String
        get() {
            val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            return documentDirectory?.path ?: ""
        }

    override val databasePath: String
        get() = "$appDataDir/twilit_music.db"
}
