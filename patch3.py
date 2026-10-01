import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'r') as f:
    content = f.read()

content = content.replace('import javax.inject.Singleton', 'import javax.inject.Singleton\nimport kotlinx.coroutines.CoroutineScope\nimport kotlinx.coroutines.Dispatchers\nimport kotlinx.coroutines.launch\nimport kotlinx.coroutines.delay')

old_state = '''    private val _queue = MutableStateFlow<List<Track>>(emptyList())
    val queue: StateFlow<List<Track>> = _queue.asStateFlow()'''
new_state = '''    private val _queue = MutableStateFlow<List<Track>>(emptyList())
    val queue: StateFlow<List<Track>> = _queue.asStateFlow()

    private val _position = MutableStateFlow(0L)
    val position: StateFlow<Long> = _position.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _bufferedPosition = MutableStateFlow(0L)
    val bufferedPosition: StateFlow<Long> = _bufferedPosition.asStateFlow()'''
content = content.replace(old_state, new_state)

old_init = '''        mediaController?.addListener(object : Player.Listener {'''
new_init = '''        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                if (_isPlaying.value) {
                    mediaController?.let {
                        _position.value = it.currentPosition
                        _duration.value = it.duration.coerceAtLeast(0L)
                        _bufferedPosition.value = it.bufferedPosition
                    }
                }
                delay(500)
            }
        }
        mediaController?.addListener(object : Player.Listener {'''
content = content.replace(old_init, new_init)

content = content.replace('fun moveTrack(fromIndex: Int, toIndex: Int) {\n        mediaController?.moveMediaItem(fromIndex, toIndex)\n    }', 'fun moveTrack(fromIndex: Int, toIndex: Int) {\n        mediaController?.moveMediaItem(fromIndex, toIndex)\n    }\n\n    fun seekTo(positionMs: Long) {\n        mediaController?.seekTo(positionMs)\n    }')

with open('app/src/main/java/com/twilitmusic/app/playback/MusicController.kt', 'w') as f:
    f.write(content)
