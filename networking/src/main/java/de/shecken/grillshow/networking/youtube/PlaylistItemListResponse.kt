package de.shecken.grillshow.networking.youtube

import kotlinx.serialization.*

@Serializable
data class PlaylistItem(
    val kind: String,
    val etag: String,
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
    val channelTitle: String,
)

@Serializable
data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail,
    val standard: Thumbnail,
    val maxres: Thumbnail
)

@Serializable
data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)

@Serializable
data class ContentDetails(
    val videoId: String,
    val videoPublishedAt: String
)

@Serializable
data class PlaylistItemListResponse(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val items: List<PlaylistItem>
)