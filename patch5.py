import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.runtime.mutableStateOf', 'import androidx.compose.runtime.mutableStateOf\nimport androidx.compose.runtime.collectAsState')

old_sig = '''    onPrev: () -> Unit,
    viewModel: MainViewModel
) {
    var showQueue by remember { mutableStateOf(false) }'''
new_sig = '''    onPrev: () -> Unit,
    viewModel: MainViewModel
) {
    var showQueue by remember { mutableStateOf(false) }
    val position by viewModel.musicController.position.collectAsState()
    val duration by viewModel.musicController.duration.collectAsState()
    var sliderPosition by remember { mutableStateOf<Float?>(null) }
    val displayPosition = sliderPosition ?: position.toFloat()'''
content = content.replace(old_sig, new_sig)

old_slider = '''                Spacer(modifier = Modifier.height(32.dp))
                Slider(value = 0f, onValueChange = {})
                Row('''
new_slider = '''                Spacer(modifier = Modifier.height(32.dp))
                Slider(
                    value = if (duration > 0) displayPosition / duration.toFloat() else 0f,
                    onValueChange = { sliderPosition = it * duration.toFloat() },
                    onValueChangeFinished = {
                        sliderPosition?.let { viewModel.musicController.seekTo(it.toLong()) }
                        sliderPosition = null
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(formatTime(displayPosition.toLong()), style = MaterialTheme.typography.labelMedium)
                    Text(formatTime(duration), style = MaterialTheme.typography.labelMedium)
                }
                Row('''
content = content.replace(old_slider, new_slider)

helper = '''@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueSheet('''
helper_new = '''private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueSheet('''
content = content.replace(helper, helper_new)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
