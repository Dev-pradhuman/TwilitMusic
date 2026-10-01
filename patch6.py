import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

old_state = '''    private val _bufferedPosition = MutableStateFlow(0L)
    val bufferedPosition: StateFlow<Long> = _bufferedPosition.asStateFlow()'''
new_state = '''    private val _bufferedPosition = MutableStateFlow(0L)
    val bufferedPosition: StateFlow<Long> = _bufferedPosition.asStateFlow()

    private val _shuffleModeEnabled = MutableStateFlow(false)
    val shuffleModeEnabled: StateFlow<Boolean> = _shuffleModeEnabled.asStateFlow()

    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_OFF)
    val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()'''
content = content.replace(old_state, new_state)

old_listener = '''            override fun onTimelineChanged(timeline: androidx.media3.common.Timeline, reason: Int) {
                updateQueue()
            }
        })'''
new_listener = '''            override fun onTimelineChanged(timeline: androidx.media3.common.Timeline, reason: Int) {
                updateQueue()
            }
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                _shuffleModeEnabled.value = shuffleModeEnabled
                updateQueue()
            }
            override fun onRepeatModeChanged(repeatMode: Int) {
                _repeatMode.value = repeatMode
            }
        })'''
content = content.replace(old_listener, new_listener)

methods = '''    fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
    }'''
new_methods = '''    fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
    }

    fun toggleShuffle() {
        val controller = mediaController ?: return
        controller.shuffleModeEnabled = !controller.shuffleModeEnabled
    }

    fun cycleRepeatMode() {
        val controller = mediaController ?: return
        controller.repeatMode = when (controller.repeatMode) {
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_OFF
        }
    }'''
content = content.replace(methods, new_methods)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
