package de.shecken.grillshow.info.interactor

import de.shecken.grillshow.info.BuildConfig
import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.vo.InfoItemVo
import de.shecken.grillshow.info.vo.SocialMediaItemVo
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class InfoInteractorImpl(private val preferencesRepository: PreferencesRepository) :
    InfoInteractor {

    override val versionName: Flow<String>
        get() = preferencesRepository.appPreferencesFlow.map { pref -> pref.appVersion }

    override val socialMediaLinks = flowOf(createSocialMediaLinks())

    override val devInfo: Flow<List<InfoItemVo>> = flowOf(createDevLinks())

    private fun createSocialMediaLinks() =
        listOf(
            SocialMediaItemVo(
                iconRes = R.drawable.ic_facebook,
                url = BuildConfig.FACEBOOK_URL
            ),
            SocialMediaItemVo(
                iconRes = R.drawable.ic_instagram,
                url = BuildConfig.INSTAGRAM_URL
            ),
            SocialMediaItemVo(
                iconRes = R.drawable.ic_tiktok,
                url = BuildConfig.TIKTOK_URL
            ),
            SocialMediaItemVo(
                iconRes = R.drawable.ic_youtube,
                url = BuildConfig.YOUTUBE_URL
            )
        )

    private fun createDevLinks() =
        listOf(
            InfoItemVo(
                titleRes = R.string.info_dev,
                subtitleRes = R.string.info_dev_subtitle,
                iconRes = R.drawable.ic_person,
            ),
            InfoItemVo(
                titleRes = R.string.info_contact,
                subtitleRes = R.string.info_contact_subtitle,
                iconRes = R.drawable.ic_mail
            )
        )
}