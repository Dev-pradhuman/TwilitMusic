package com.twilitmusic.app.domain

import com.twilitmusic.app.domain.model.Track
import android.content.Context
import android.content.Intent

class AndroidDownloadManager(private val context: Context) : TwilitDownloadManager {
    override fun download(track: Track) {
        // Start the actual foreground service in androidApp module
        // We use reflection or explicit Intent since TwilitDownloadService is in androidApp
        val intent = Intent()
        intent.setClassName(context, "com.twilitmusic.app.playback.TwilitDownloadService")
        intent.putExtra("track_id", track.id)
        intent.putExtra("track_title", track.title)
        intent.putExtra("track_url", track.sourceUrl)
        context.startService(intent)
    }
}
