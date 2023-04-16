package de.shecken.grillshow.info.interactor

import de.shecken.grillshow.info.vo.InfoItemVo
import de.shecken.grillshow.info.vo.SocialMediaItemVo
import kotlinx.coroutines.flow.Flow

interface InfoInteractor {

    /**
     * Current version name as flow
     */
    val versionName: Flow<String>

    /**
     * List of social media links as flow
     */
    val socialMediaLinks: Flow<List<SocialMediaItemVo>>

    /**
     * List of dev links as flow
     */
    val devInfo: Flow<List<InfoItemVo>>
}