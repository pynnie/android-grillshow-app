package de.shecken.grillshow.repository.video

import kotlinx.coroutines.flow.Flow

interface VideoRepository {

   suspend fun loadAllVideos()
    val videos: Flow<List<PlaylistItem>>
    val playLists: Flow<List<PlayList>>

    suspend fun loadPlayListItemsById(id: String): List<PlaylistItem>
    suspend fun fetchAllPlaylists(): List<PlayList>
}