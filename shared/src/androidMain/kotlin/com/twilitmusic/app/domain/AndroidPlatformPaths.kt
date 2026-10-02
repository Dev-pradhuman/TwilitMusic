package com.twilitmusic.app.domain

import android.content.Context

class AndroidPlatformPaths(private val context: Context) : PlatformPaths {
    override val appDataDir: String
        get() = context.filesDir.absolutePath

    override val databasePath: String
        get() = context.getDatabasePath("twilit_music.db").absolutePath
}
