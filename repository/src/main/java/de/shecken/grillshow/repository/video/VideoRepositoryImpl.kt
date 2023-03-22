package de.shecken.grillshow.repository.video

import de.shecken.grillshow.networking.youtube.YoutubeDataApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class VideoRepositoryImpl(
    private val api: YoutubeDataApi
) : VideoRepository {



    private val _videos = MutableStateFlow<List<PlaylistItem>>(emptyList())
    override val videos: Flow<List<PlaylistItem>>
        get() = _videos

    private val _playLists = MutableStateFlow<List<PlayList>>(emptyList())
    override val playLists: Flow<List<PlayList>>
        get() = _playLists

    override suspend fun loadAllVideos() = withContext(Dispatchers.IO) {
        val vids = api.requestAllVideosFromChannel().items.map {
            PlaylistItem(
                it.snippet.title,
                it.snippet.description,
                it.snippet.thumbnails.default.url
            )
        }
        _videos.emit(vids)
    }

    override suspend fun fetchAllPlaylists() = withContext(Dispatchers.IO) {
        api.requestsPlaylistsFromChannel().items.map { dto ->
             val playListItems = api.requestPlayListItemsForId(playlistId = dto.id).items.map { item ->
                PlaylistItem(
                    item.snippet.title,
                    item.snippet.description,
                    item.snippet.thumbnails.default.url
                )
            }
            PlayList(
                id = dto.id,
                title = dto.snippet.title,
                items = playListItems
            )
        }
    }

    override suspend fun loadPlayListItemsById(id: String) = withContext(Dispatchers.IO) {
        api.requestPlayListItemsForId(playlistId = id).items.map { item ->
            PlaylistItem(
                item.snippet.title,
                item.snippet.description,
                item.snippet.thumbnails.default.url
            )
        }
    }
}
