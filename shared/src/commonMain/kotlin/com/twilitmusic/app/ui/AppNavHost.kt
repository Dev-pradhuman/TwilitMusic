package com.twilitmusic.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,

) {
    val uiState = viewModel.uiState.value
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(uiState = uiState, onPlayTrack = viewModel::playTrack)
        }
        composable<SearchRoute> {
            SearchScreen(viewModel = viewModel, onTrackClick = { viewModel.playTrack(it) })
        }
        composable<LibraryRoute> {
            LibraryScreen(onTrackClick = { viewModel.playTrack(it) }, onPlaylistClick = { navController.navigate(PlaylistDetailRoute(it)) })
        }
        composable<PlaylistDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<PlaylistDetailRoute>()
            PlaylistDetailScreen(playlistId = route.playlistId, onTrackClick = { viewModel.playTrack(it) })
        }
        composable<AlbumDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AlbumDetailRoute>()
            AlbumDetailScreen(album = route.album, viewModel = viewModel, onTrackClick = { viewModel.playTrack(it) })
        }
        composable<ArtistDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ArtistDetailRoute>()
            ArtistDetailScreen(artist = route.artist, viewModel = viewModel, onTrackClick = { viewModel.playTrack(it) })
        }
    }
}
