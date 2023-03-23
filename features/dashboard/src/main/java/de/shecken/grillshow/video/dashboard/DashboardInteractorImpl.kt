package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.video.VideoRepository

internal class DashboardInteractorImpl(private val videoRepo: VideoRepository) : DashboardInteractor {

    override suspend fun getVideos() = videoRepo.playLists.also { videoRepo.fetchAllPlaylists() }
}
