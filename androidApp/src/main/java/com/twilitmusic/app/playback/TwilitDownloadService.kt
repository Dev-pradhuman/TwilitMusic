package com.twilitmusic.app.playback

import android.app.Notification
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadService
import com.twilitmusic.app.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class TwilitDownloadService : DownloadService(
    1,
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    "TwilitDownloads",
    R.string.app_name,
    0
) {
    @Inject lateinit var injectedDownloadManager: DownloadManager

    override fun getDownloadManager(): DownloadManager = injectedDownloadManager

    override fun getScheduler(): androidx.media3.exoplayer.scheduler.Scheduler? = null

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
