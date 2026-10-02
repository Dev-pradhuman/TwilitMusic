import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainScreen.kt'
with open(path, 'r') as f:
    content = f.read()

old_keys = '''                    when (event.key) {
                        Key.Spacebar -> { viewModel.playPause(); true }
                        Key.DirectionRight -> { viewModel.skipToNext(); true }
                        Key.DirectionLeft -> { viewModel.skipToPrevious(); true }
                        else -> false
                    }'''

new_keys = '''                    when {
                        event.key == Key.Spacebar && !event.isCtrlPressed -> { viewModel.playPause(); true }
                        event.key == Key.DirectionRight && event.isCtrlPressed -> { viewModel.skipToNext(); true }
                        event.key == Key.DirectionLeft && event.isCtrlPressed -> { viewModel.skipToPrevious(); true }
                        event.key == Key.F && event.isCtrlPressed -> { currentTab = 1; navController.navigate("search") { launchSingleTop = true }; true }
                        else -> false
                    }'''

content = content.replace(old_keys, new_keys)

# Ensure isCtrlPressed is imported, but it's part of KeyEvent which we have.
# Actually, it's `event.isCtrlPressed`. Let's ensure it's available.

with open(path, 'w') as f:
    f.write(content)
