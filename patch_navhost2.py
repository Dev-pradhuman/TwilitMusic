import sys

with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.navigation.compose.composable', 'import androidx.navigation.compose.composable\nimport androidx.navigation.toRoute')

old_routes = '''        composable<PlaylistDetailRoute> {
            // TODO: Playlist Detail Screen
        }
        composable<AlbumDetailRoute> {
            // TODO: Album Detail Screen
        }
        composable<ArtistDetailRoute> {
            // TODO: Artist Detail Screen
        }'''
new_routes = '''        composable<PlaylistDetailRoute> {
            PlaylistDetailScreen(onTrackClick = { viewModel.playTrack(it) })
        }
        composable<AlbumDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AlbumDetailRoute>()
            AlbumDetailScreen(album = route.album, viewModel = viewModel, onTrackClick = { viewModel.playTrack(it) })
        }
        composable<ArtistDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ArtistDetailRoute>()
            ArtistDetailScreen(artist = route.artist, viewModel = viewModel, onTrackClick = { viewModel.playTrack(it) })
        }'''
content = content.replace(old_routes, new_routes)

with open('app/src/main/java/com/twilitmusic/app/ui/AppNavHost.kt', 'w') as f:
    f.write(content)
