package com.twilitmusic.app.ui

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object SearchRoute

@Serializable
object LibraryRoute

@Serializable
data class PlaylistDetailRoute(val playlistId: Long)

@Serializable
data class AlbumDetailRoute(val album: String)

@Serializable
data class ArtistDetailRoute(val artist: String)
