import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainScreen.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }', 'val focusRequester = remember { FocusRequester() }')
content = content.replace('.androidx.compose.ui.focus.focusRequester(focusRequester)', '.focusRequester(focusRequester)')

content = 'import androidx.compose.ui.focus.FocusRequester\nimport androidx.compose.ui.focus.focusRequester\n' + content

with open(path, 'w') as f:
    f.write(content)
