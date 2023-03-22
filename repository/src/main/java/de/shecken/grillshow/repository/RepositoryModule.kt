package de.shecken.grillshow.repository

import de.shecken.grillshow.repository.video.VideoRepository
import de.shecken.grillshow.repository.video.VideoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<VideoRepository> { VideoRepositoryImpl(get()) }
}
