package de.shecken.grillshow.networking.youtube

import de.shecken.networking.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YoutubeDataApi {

    @GET("playlistItems")
    @Headers("Content-Type: application/json")
    suspend fun requestLatestUploads(
        @Query(PARAM_API_KEY) key: String = BuildConfig.YOUTUBE_DATA_API_KEY,
        @Query(PARAM_PLAYLIST_ID) playlistId: String = BuildConfig.GRILLSHOW_UPLOADS_PLAYLIST_ID,
        @Query(PARAM_PART) part: String = DEFAULT_PART,
        @Query(PARAM_ORDER) order: String = DEFAULT_ORDER,
        @Query(PARAM_PAGE_TOKEN) pageToken: String = "",
        @Query(PARAM_MAX_RESULTS) maxResults: Int = DEFAULT_MAX_RESULTS
    ): PlaylistItemListResponse

    companion object {
        private const val PARAM_API_KEY = "key"
        private const val PARAM_PLAYLIST_ID = "playlistId"
        private const val PARAM_PART = "part"
        private const val PARAM_ORDER = "order"
        private const val PARAM_PAGE_TOKEN = "pageToken"
        private const val PARAM_MAX_RESULTS = "maxResults"

        private const val DEFAULT_ORDER = "date"
        private const val DEFAULT_PART = "snippet, contentDetails"
        private const val DEFAULT_MAX_RESULTS = 50
    }
}