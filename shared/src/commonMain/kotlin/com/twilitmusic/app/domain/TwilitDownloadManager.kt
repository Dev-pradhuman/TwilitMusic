package com.twilitmusic.app.domain

import com.twilitmusic.app.domain.model.Track

interface TwilitDownloadManager {
    fun download(track: Track)
}
