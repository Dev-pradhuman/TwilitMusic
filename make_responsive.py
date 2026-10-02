import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/MainScreen.kt'
with open(path, 'r') as f:
    content = f.read()

responsive_ui = '''    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth > 600.dp

        if (isWideScreen) {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRail(
                    header = {
                        // Optional logo
                    }
                ) {
                    NavigationRailItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0; navController.navigate(HomeRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Home, contentDescription = "") },
                        label = { Text("Home") }
                    )
                    NavigationRailItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1; navController.navigate(SearchRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Search, contentDescription = "") },
                        label = { Text("Search") }
                    )
                    NavigationRailItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2; navController.navigate(LibraryRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.LibraryMusic, contentDescription = "") },
                        label = { Text("Library") }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (currentTrack != null) {
                        IconButton(onClick = { showNowPlaying = true }) {
                            Icon(Icons.Default.LibraryMusic, contentDescription = "Now Playing")
                        }
                    }
                }
                
                Scaffold(
                    bottomBar = {
                        if (currentTrack != null) {
                            MiniPlayer(
                                track = currentTrack!!,
                                isPlaying = isPlaying,
                                progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                                onPlayPause = viewModel::playPause,
                                onClick = { showNowPlaying = true }
                            )
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        AppNavHost(navController = navController, viewModel = viewModel)
                    }
                }
            }
        } else {
            Scaffold(
                bottomBar = {
                    Column {
                        if (currentTrack != null) {
                            MiniPlayer(
                                track = currentTrack!!,
                                isPlaying = isPlaying,
                                progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                                onPlayPause = viewModel::playPause,
                                onClick = { showNowPlaying = true }
                            )
                        }
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentTab == 0,
                                onClick = { currentTab = 0; navController.navigate(HomeRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                                icon = { Icon(Icons.Default.Home, contentDescription = "") },
                                label = { Text("Home") }
                            )
                            NavigationBarItem(
                                selected = currentTab == 1,
                                onClick = { currentTab = 1; navController.navigate(SearchRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                                icon = { Icon(Icons.Default.Search, contentDescription = "") },
                                label = { Text("Search") }
                            )
                            NavigationBarItem(
                                selected = currentTab == 2,
                                onClick = { currentTab = 2; navController.navigate(LibraryRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                                icon = { Icon(Icons.Default.LibraryMusic, contentDescription = "") },
                                label = { Text("Library") }
                            )
                        }
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    AppNavHost(navController = navController, viewModel = viewModel)
                }
            }
        }
    }'''

old_ui = '''    Scaffold(
        bottomBar = {
            Column {
                if (currentTrack != null) {
                    MiniPlayer(
                        track = currentTrack!!,
                        isPlaying = isPlaying,
                        progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f,
                        onPlayPause = viewModel::playPause,
                        onClick = { showNowPlaying = true }
                    )
                }
                NavigationBar {
                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0; navController.navigate(HomeRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Home, contentDescription = "") },
                        label = { Text("") }
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1; navController.navigate(SearchRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.Search, contentDescription = "") },
                        label = { Text("") }
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2; navController.navigate(LibraryRoute) { popUpTo(navController.graph.findStartDestination().route!!) { saveState = true }; launchSingleTop = true; restoreState = true } },
                        icon = { Icon(Icons.Default.LibraryMusic, contentDescription = "") },
                        label = { Text("") }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(navController = navController, viewModel = viewModel)
        }
    }'''

content = content.replace(old_ui, responsive_ui)
with open(path, 'w') as f:
    f.write(content)
