package de.shecken.grillshow.networking.youtube

import kotlinx.serialization.Serializable

@Serializable
data class PlayListItemsDto(
    val items: List<VideoItemDto>
)

@Serializable
data class VideoItemDto(
    val id: String,
    val snippet: VideoSnippetDto
)

@Serializable
data class VideoSnippetDto(
    val publishedAt: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails
)

@Serializable
data class Thumbnails(
    val default: ThumbnailDto,
    val medium: ThumbnailDto,
    val high: ThumbnailDto
)

@Serializable
data class ThumbnailDto(
    val url: String,
    val width: Int,
    val height: Int
)