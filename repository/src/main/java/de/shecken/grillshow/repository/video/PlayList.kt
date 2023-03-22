package de.shecken.grillshow.repository.video

data class PlayList(
    val id: String,
    val title: String,
    val items: List<PlaylistItem>
)
