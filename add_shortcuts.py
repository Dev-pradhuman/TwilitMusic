import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainScreen.kt'
with open(path, 'r') as f:
    content = f.read()

content = 'import androidx.compose.ui.input.key.*\n' + content

# In TwilitAppScreen, we can add a FocusRequester and onKeyEvent
# Let's just modify TwilitAppScreen signature and root BoxWithConstraints

twilit_screen_old = 'BoxWithConstraints(modifier = Modifier.fillMaxSize()) {'
twilit_screen_new = '''    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .androidx.compose.ui.focus.focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyUp) {
                    when (event.key) {
                        Key.Spacebar -> { viewModel.playPause(); true }
                        Key.DirectionRight -> { viewModel.skipToNext(); true }
                        Key.DirectionLeft -> { viewModel.skipToPrevious(); true }
                        else -> false
                    }
                } else false
            }
    ) {'''

content = content.replace(twilit_screen_old, twilit_screen_new)

with open(path, 'w') as f:
    f.write(content)
