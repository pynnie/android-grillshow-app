package de.shecken.grillshow.networking.youtube

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistResponse(
    val items: List<Playlist>
)

@Serializable
data class Playlist(
    val id: String,
    val snippet: PlaylistSnippet
)

@Serializable
data class PlaylistSnippet(
    val title: String,
    val description: String,
)