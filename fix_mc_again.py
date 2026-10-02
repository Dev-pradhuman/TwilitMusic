import re

path = 'androidApp/src/main/java/com/twilitmusic/app/playback/MusicController.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('override fun playQueue(tracks: List<Track>, startIndex: Int = 0)', 'override fun playQueue(tracks: List<Track>, startIndex: Int)')
content = content.replace('fun moveTrack(fromIndex: Int, toIndex: Int)', 'override fun moveTrack(from: Int, to: Int)')

# Add missing methods
missing_methods = '''
    override fun setRepeatMode(mode: Int) {
        mediaController?.repeatMode = mode
    }

    override fun setShuffleModeEnabled(enabled: Boolean) {
        mediaController?.shuffleModeEnabled = enabled
    }
}'''
content = content.replace('}\n\n    fun cycleRepeatMode()', '}\n\n    override fun setRepeatMode(mode: Int) { mediaController?.repeatMode = mode }\n    override fun setShuffleModeEnabled(enabled: Boolean) { mediaController?.shuffleModeEnabled = enabled }\n\n    fun cycleRepeatMode()')

with open(path, 'w') as f:
    f.write(content)
