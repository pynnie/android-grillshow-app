package de.shecken.grillshow.info.ui

import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.vo.InfoItemVo
import de.shecken.grillshow.info.vo.SocialMediaItemVo

internal val devInfo = InfoItemVo(
    titleRes = R.string.info_dev,
    subtitleRes = R.string.info_dev_subtitle,
    iconRes = R.drawable.ic_github,
)

internal val contactInfo = InfoItemVo(
    titleRes = R.string.info_mail,
    subtitleRes = R.string.info_contact_mail_address,
    iconRes = R.drawable.ic_mail
)

internal val termsInfo = InfoItemVo(
    titleRes = R.string.info_terms,
    iconRes = R.drawable.ic_terms
)

internal val privacyInfo = InfoItemVo(
    titleRes = R.string.info_privacy,
    iconRes = R.drawable.ic_privacy
)

internal val licensesInfo = InfoItemVo(
    titleRes = R.string.info_licenses,
    iconRes = R.drawable.ic_license
)

internal val socialMediaLinks =
    listOf(
        SocialMediaItemVo(
            iconRes = R.drawable.ic_facebook,
            urlRes = R.string.facebook_url
        ),
        SocialMediaItemVo(
            iconRes = R.drawable.ic_instagram,
            urlRes = R.string.instagram_url
        ),
        SocialMediaItemVo(
            iconRes = R.drawable.ic_tiktok,
            urlRes = R.string.tiktok_url
        ),
        SocialMediaItemVo(
            iconRes = R.drawable.ic_youtube,
            urlRes = R.string.youtube_url
        )
    )