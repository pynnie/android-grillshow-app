package de.shecken.grillshow.networking.youtube

import kotlinx.serialization.Serializable

@Serializable
data class PlayListDto(
    val items: List<PlayListInfoDto>
)

@Serializable
data class PlayListInfoDto(
    val id: String,
    val snippet: PlayListInfoSnippetDto
)

@Serializable
data class PlayListInfoSnippetDto(
    val title: String
)
