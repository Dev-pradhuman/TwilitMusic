import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

old_update_queue = '''        _queue.value = newQueue
        updateCurrentTrack(controller.currentMediaItem)
    }'''
new_update_queue = '''        _queue.value = newQueue
        updateCurrentTrack(controller.currentMediaItem)
        saveQueueState()
    }
    
    private fun saveQueueState() {
        val prefs = context.getSharedPreferences("music_prefs", android.content.Context.MODE_PRIVATE)
        val trackIds = _queue.value.joinToString(",") { it.id }
        val index = mediaController?.currentMediaItemIndex ?: 0
        prefs.edit()
            .putString("saved_queue", trackIds)
            .putInt("saved_index", index)
            .apply()
    }'''
content = content.replace(old_update_queue, new_update_queue)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
