import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.runtime.*', 'import androidx.compose.runtime.*\nimport androidx.compose.foundation.background\nimport androidx.compose.ui.graphics.Color')

banner_code = '''
    val isOffline by viewModel.isOffline.collectAsState()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("TwilitMusic") }
                )
                if (isOffline) {
                    Text("You are offline", modifier = Modifier.fillMaxWidth().background(Color.Red).padding(8.dp), color = Color.White)
                }
            }
        },'''

if 'val isOffline by viewModel' not in content:
    content = content.replace('''    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TwilitMusic") }
            )
        },''', banner_code)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
