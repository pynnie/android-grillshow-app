package de.shecken.grillshow.networking.youtube.response

import kotlinx.serialization.*

@Serializable
data class PlaylistItem(
    val id: String,
    val snippet: Snippet,
    val contentDetails: ContentDetails
)

@Serializable
data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
)

@Serializable
data class Thumbnails(
    val default: Thumbnail? = null,
    val standard: Thumbnail? = null,
    val medium: Thumbnail? = null,
    val high: Thumbnail? = null
)

@Serializable
data class Thumbnail(
    val url: String
)

@Serializable
data class ContentDetails(
    val videoId: String,
    val videoPublishedAt: String
)

@Serializable
data class PlaylistItemListResponse(
    val nextPageToken: String? = null,
    val items: List<PlaylistItem>
)