import re

path = 'androidApp/src/main/java/com/twilitmusic/app/playback/MusicController.kt'
with open(path, 'r') as f:
    content = f.read()

# Imports
content = re.sub(r'import dagger\.hilt\..*\n', '', content)
content = re.sub(r'import javax\.inject\..*\n', '', content)
content = content.replace('import com.twilitmusic.app.data.local.entity.PlaybackStateEntity', 'import com.twilitmusic.app.data.local.entity.PlaybackStateEntity\nimport com.twilitmusic.app.playback.AudioPlayer')

# Annotations & Constructor
content = re.sub(r'@Singleton\nclass MusicController @Inject constructor\(\n\s*@ApplicationContext private val context: Context,', 'class MusicController (\n    private val context: Context,', content, flags=re.DOTALL)
content = content.replace('class MusicController (', 'class MusicController (')
content = re.sub(r'class MusicController \([\s\S]*?\) \{', lambda m: m.group(0).replace(') {', ') : AudioPlayer {'), content)

# Methods
content = content.replace('suspend fun init()', 'override suspend fun init()')
content = content.replace('fun playQueue(', 'override fun playQueue(')
content = content.replace('fun playPause()', 'override fun playPause()')
content = content.replace('fun skipToNext()', 'override fun skipToNext()')
content = content.replace('fun skipToPrevious()', 'override fun skipToPrevious()')
content = content.replace('fun removeTrack(index: Int)', 'override fun removeTrack(index: Int)')
content = content.replace('fun moveTrack(from: Int, to: Int)', 'override fun moveTrack(from: Int, to: Int)')

# Properties
content = content.replace('val currentTrack:', 'override val currentTrack:')
content = content.replace('val queue:', 'override val queue:')
content = content.replace('val isPlaying:', 'override val isPlaying:')
content = content.replace('val position:', 'override val currentPosition:')
content = content.replace('val duration:', 'override val duration:')
content = content.replace('val repeatMode:', 'override val repeatMode:')
content = content.replace('val shuffleModeEnabled:', 'override val shuffleModeEnabled:')

# Add seekTo and setRepeatMode / setShuffleModeEnabled if they don't exist
# We will just append them before the last brace if they don't exist
if 'fun seekTo' not in content:
    content = content.replace('}\n\n    private fun syncState', '}\n\n    override fun seekTo(position: Long) { mediaController?.seekTo(position) }\n    override fun setRepeatMode(mode: Int) { mediaController?.repeatMode = mode }\n    override fun setShuffleModeEnabled(enabled: Boolean) { mediaController?.shuffleModeEnabled = enabled }\n\n    private fun syncState')

if 'override fun seekTo' not in content:
    content = content.replace('fun seekTo(', 'override fun seekTo(')

if 'override fun setRepeatMode' not in content:
    content = content.replace('fun setRepeatMode(', 'override fun setRepeatMode(')

if 'override fun setShuffleModeEnabled' not in content:
    content = content.replace('fun setShuffleModeEnabled(', 'override fun setShuffleModeEnabled(')

with open(path, 'w') as f:
    f.write(content)

