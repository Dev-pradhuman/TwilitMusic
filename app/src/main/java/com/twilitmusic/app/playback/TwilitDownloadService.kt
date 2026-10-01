package com.twilitmusic.app.playback

import android.app.Notification
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadService
import com.twilitmusic.app.R
import java.util.concurrent.Executor

class TwilitDownloadService : DownloadService(
    1,
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    "TwilitDownloads",
    R.string.app_name,
    0
) {
    override fun getDownloadManager(): DownloadManager {
        val databaseProvider = androidx.media3.database.StandaloneDatabaseProvider(this)
        val downloadCache = CacheManager.getInstance(this)
        val dataSourceFactory = androidx.media3.datasource.DefaultDataSource.Factory(this)
        val executor = Executor { it.run() }
        
        return DownloadManager(
            this,
            databaseProvider,
            downloadCache,
            dataSourceFactory,
            executor
        )
    }

    override fun getScheduler(): androidx.media3.exoplayer.scheduler.Scheduler? {
        return null // No scheduler for now
    }

    override fun getForegroundNotification(
        downloads: MutableList<Download>,
        notMetRequirements: Int
    ): Notification {
        return androidx.media3.exoplayer.offline.DownloadNotificationHelper(
            this, "TwilitDownloads"
        ).buildProgressNotification(
            this,
            R.mipmap.ic_launcher,
            null,
            null,
            downloads,
            notMetRequirements
        )
    }
}
