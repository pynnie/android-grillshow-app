@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package de.shecken.grillshow.info.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.vo.InfoItemVo
import de.shecken.grillshow.info.vo.SocialMediaItemVo
import de.shecken.grillshow.shared.GrillshowTheme

import org.koin.androidx.compose.getViewModel


@Composable
fun InfoScreen(viewModel: InfoViewModel = getViewModel()) {

    val version by viewModel.versionName.collectAsStateWithLifecycle()
    val socialMediaList by viewModel.socialMediaLinks.collectAsStateWithLifecycle()
    val devInfoList by viewModel.devInfo.collectAsStateWithLifecycle()
    InfoScreen(version = version,
        socialMediaList = socialMediaList,
        onSocialMediaItemClick = viewModel::onSocialMediaLinkClicked,
        devInfoList = devInfoList,
        onContactClick = {})
}

@Composable
private fun InfoScreen(
    version: String,
    socialMediaList: List<SocialMediaItemVo>,
    devInfoList: List<InfoItemVo>,
    onContactClick: () -> Unit,
    onSocialMediaItemClick: (String) -> Unit
) {
    Scaffold(modifier = Modifier.padding(horizontal = 8.dp), topBar = { InfoTopBar() }) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            InfoScreenContent(
                version = version,
                socialMediaList = socialMediaList,
                onSocialMediaItemClick = onSocialMediaItemClick,
                devInfoList = devInfoList,
                onContactClick = onContactClick
            )
        }
    }

}

@Composable
private fun InfoTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.info_title)) })
}

@Composable
private fun InfoScreenContent(
    version: String,
    socialMediaList: List<SocialMediaItemVo>,
    devInfoList: List<InfoItemVo>,
    onContactClick: () -> Unit,
    onSocialMediaItemClick: (String) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        InfoHeader(modifier = Modifier.padding(bottom = 8.dp), version = version)

        Divider()

        DevInfoBlock(
            modifier = Modifier.padding(vertical = 8.dp),
            devInfoList = devInfoList, onContactClick = onContactClick
        )

        SocialMediaList(
            socialMediaList = socialMediaList, onSocialMediaItemClick = onSocialMediaItemClick
        )
    }
}

@Composable
private fun InfoHeader(modifier: Modifier = Modifier, version: String) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(200.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.info_version, version))

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun SocialMediaList(
    modifier: Modifier = Modifier,
    socialMediaList: List<SocialMediaItemVo>,
    onSocialMediaItemClick: (String) -> Unit
) {
    Card(modifier = modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            socialMediaList.forEach { socialMediaItem ->
                SocialMediaItem(
                    socialMediaItem = socialMediaItem,
                    onSocialMediaItemClick = onSocialMediaItemClick
                )
            }
        }
    }
}

@Composable
private fun DevInfoBlock(
    modifier: Modifier = Modifier, devInfoList: List<InfoItemVo>, onContactClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            devInfoList.forEach { devInfo ->
                DevInfoItem(devInfo = devInfo)
            }
        }
    }
}

@Composable
private fun DevInfoItem(modifier: Modifier = Modifier, devInfo: InfoItemVo) {
    Row(
        modifier = modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = devInfo.iconRes),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = stringResource(id = devInfo.titleRes),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = devInfo.subtitleRes),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun SocialMediaItem(socialMediaItem: SocialMediaItemVo, onSocialMediaItemClick: (String) -> Unit) {
    IconButton(

        onClick = { onSocialMediaItemClick(socialMediaItem.url) }) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = socialMediaItem.iconRes),
            contentDescription = ""
        )
    }
}

@Composable
@Preview
private fun InfoHeaderPreview() {
    GrillshowTheme {
        InfoHeader(version = "1.0")
    }
}

@Composable
@Preview
private fun DevInfoPreview() {
    GrillshowTheme {
        DevInfoItem(
            devInfo = InfoItemVo(
                R.string.info_dev, R.string.info_dev_subtitle, R.drawable.ic_person
            )
        )
    }
}
