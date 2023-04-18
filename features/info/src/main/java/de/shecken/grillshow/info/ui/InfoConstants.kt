package de.shecken.grillshow.info.ui

import de.shecken.grillshow.info.BuildConfig
import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.vo.InfoItemVo
import de.shecken.grillshow.info.vo.SocialMediaItemVo

internal val devInfo = InfoItemVo(
    titleRes = R.string.info_dev,
    subtitleRes = R.string.info_dev_subtitle,
    iconRes = R.drawable.ic_person,
)

internal val contactInfo = InfoItemVo(
    titleRes = R.string.info_contact,
    subtitleRes = R.string.info_contact_mail,
    iconRes = R.drawable.ic_mail
)

internal val socialMediaLinks =
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