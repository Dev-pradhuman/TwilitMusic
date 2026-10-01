import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.ui.Modifier', 'import androidx.compose.ui.Modifier\nimport androidx.navigation.compose.rememberNavController\nimport androidx.navigation.NavGraph.Companion.findStartDestination')

old_state = '''    var currentTab by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsState()'''
new_state = '''    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsState()'''
content = content.replace(old_state, new_state)

old_nav = '''                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
                        label = { Text(stringResource(R.string.home)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1 },
                        icon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search)) },
                        label = { Text(stringResource(R.string.search)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2 },
                        icon = { Icon(Icons.Default.LibraryMusic, contentDescription = stringResource(R.string.library)) },
                        label = { Text(stringResource(R.string.library)) }
                    )'''
new_nav = '''                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0; navController.navigate(HomeRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
                        label = { Text(stringResource(R.string.home)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1; navController.navigate(SearchRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search)) },
                        label = { Text(stringResource(R.string.search)) }
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2; navController.navigate(LibraryRoute) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.LibraryMusic, contentDescription = stringResource(R.string.library)) },
                        label = { Text(stringResource(R.string.library)) }
                    )'''
content = content.replace(old_nav, new_nav)

old_switch = '''            when (currentTab) {
                0 -> HomeScreen(uiState, onPlayTrack = viewModel::playTrack)
                1 -> SearchScreen(viewModel, onTrackClick = { viewModel.playTrack(it) })
                2 -> LibraryScreen(onTrackClick = { viewModel.playTrack(it) })
            }'''
new_switch = '''            AppNavHost(navController = navController, viewModel = viewModel)'''
content = content.replace(old_switch, new_switch)

with open('app/src/main/java/com/twilitmusic/app/ui/MainScreen.kt', 'w') as f:
    f.write(content)
