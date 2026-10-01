import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

old_fun = '''    fun playQueue(tracks: List<Track>, startIndex: Int = 0) {
        val controller = mediaController ?: return
        val items = tracks.map { track ->
            MediaItem.Builder()
                .setMediaId(track.id)
                .setUri(track.sourceUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(track.title)
                        .setArtist(track.artist)
                        .setArtworkUri(android.net.Uri.parse(track.artUrl))
                        .build()
                )
                .build()
        }
        controller.setMediaItems(items, startIndex, 0L)
        controller.prepare()
        controller.play()
    }'''
new_fun = '''    fun playQueue(tracks: List<Track>, startIndex: Int = 0) {
        val controller = mediaController ?: return
        val items = tracks.map { track ->
            MediaItem.Builder()
                .setMediaId(track.id)
                .setUri(track.sourceUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(track.title)
                        .setArtist(track.artist)
                        .setArtworkUri(android.net.Uri.parse(track.artUrl))
                        .build()
                )
                .build()
        }
        controller.setMediaItems(items, startIndex, 0L)
        controller.prepare()
        controller.play()
    }

    fun setQueueWithoutPlaying(tracks: List<Track>, startIndex: Int = 0) {
        val controller = mediaController ?: return
        val items = tracks.map { track ->
            MediaItem.Builder()
                .setMediaId(track.id)
                .setUri(track.sourceUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(track.title)
                        .setArtist(track.artist)
                        .setArtworkUri(android.net.Uri.parse(track.artUrl))
                        .build()
                )
                .build()
        }
        controller.setMediaItems(items, startIndex, 0L)
        controller.prepare()
        // Do not play automatically
    }'''
content = content.replace(old_fun, new_fun)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
