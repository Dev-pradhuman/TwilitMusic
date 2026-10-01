import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt'
with open(path, 'r') as f:
    content = f.read()

queue_sheet_regex = r'@OptIn\(ExperimentalMaterial3Api::class\)\n@Composable\nfun QueueSheet\(.*'
dummy_queue = '''@Composable
fun QueueSheet(viewModel: MainViewModel, onClose: () -> Unit) {
    androidx.compose.material3.Text("Queue Sheet")
}'''
content = re.sub(queue_sheet_regex, dummy_queue, content, flags=re.DOTALL)

with open(path, 'w') as f:
    f.write(content)

path2 = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/TrackItem.kt'
with open(path2, 'r') as f:
    content2 = f.read()
content2 = content2.replace('Icons.Default.Check', 'Icons.Default.PlayArrow')
with open(path2, 'w') as f:
    f.write(content2)

