with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/AppNavHost.kt', 'r') as f:
    content = f.read()

old_block = '''composable<PlaylistDetailRoute> {
            PlaylistDetailScreen(onTrackClick = { viewModel.playTrack(it) })
        }'''
new_block = '''composable<PlaylistDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<PlaylistDetailRoute>()
            PlaylistDetailScreen(playlistId = route.playlistId, onTrackClick = { viewModel.playTrack(it) })
        }'''

content = content.replace(old_block, new_block)
with open('shared/src/commonMain/kotlin/com/twilitmusic/app/ui/AppNavHost.kt', 'w') as f:
    f.write(content)
