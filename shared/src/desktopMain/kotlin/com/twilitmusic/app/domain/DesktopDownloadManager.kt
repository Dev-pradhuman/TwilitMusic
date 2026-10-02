package com.twilitmusic.app.domain

import com.twilitmusic.app.domain.model.Track

class DesktopDownloadManager : TwilitDownloadManager {
    override fun download(track: Track) {
        // Simple stub for now. A real implementation would download the file
        // and put it into the Desktop Cache directory.
        println("DesktopDownloadManager: Requested download for ${track.title}")
    }
}
