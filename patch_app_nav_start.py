import sys

with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'r') as f:
    content = f.read()

content = content.replace('    startDestination: Any = HomeRoute', '')
content = content.replace('    val uiState = viewModel.uiState.value', '    val uiState = viewModel.uiState.value')
content = content.replace('startDestination = startDestination', 'startDestination = HomeRoute')

with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'w') as f:
    f.write(content)
