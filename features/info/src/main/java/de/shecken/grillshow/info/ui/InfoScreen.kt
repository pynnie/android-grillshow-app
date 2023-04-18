@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package de.shecken.grillshow.info.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
    InfoScreen(
        version = version,
        onDevInfoClick = viewModel::onDevInfoClick,
        onSocialMediaItemClick = viewModel::onSocialMediaLinkClicked,
        onContactClick = viewModel::onContactClick,
        onTermsClick = viewModel::onTermsClick,
        onPrivacyClick = viewModel::onPrivacyClick,
        onLicensesClick = viewModel::onLicensesClick
    )
}

@Composable
private fun InfoScreen(
    version: String,
    onDevInfoClick: () -> Unit,
    onContactClick: () -> Unit,
    onSocialMediaItemClick: (Int) -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLicensesClick: () -> Unit
) {
    Scaffold(modifier = Modifier
        .padding(horizontal = 8.dp),
        topBar = { InfoTopBar() }) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            InfoScreenContent(
                version = version,
                onDevInfoClick = onDevInfoClick,
                onSocialMediaItemClick = onSocialMediaItemClick,
                onContactClick = onContactClick,
                onTermsClick = onTermsClick,
                onPrivacyClick = onPrivacyClick,
                onLicensesClick = onLicensesClick
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
    onDevInfoClick: () -> Unit,
    onContactClick: () -> Unit,
    onSocialMediaItemClick: (Int) -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLicensesClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        InfoHeader(modifier = Modifier.padding(bottom = 8.dp), version = version)

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        SocialMediaList(
            onSocialMediaItemClick = onSocialMediaItemClick
        )
        Spacer(modifier = Modifier.height(16.dp))

        InfoLinks(onDevInfoClick, onContactClick, onTermsClick, onPrivacyClick, onLicensesClick)
    }
}

@Composable
private fun InfoLinks(
    onDevInfoClick: () -> Unit,
    onContactClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLicensesClick: () -> Unit
) {
    InfoItem(devInfo = devInfo, onClick = onDevInfoClick)
    InfoItem(devInfo = contactInfo, onClick = onContactClick)
    InfoItem(devInfo = termsInfo, onClick = onTermsClick)
    InfoItem(devInfo = privacyInfo, onClick = onPrivacyClick)
    InfoItem(devInfo = licensesInfo, onClick = onLicensesClick)
}

@Composable
private fun InfoHeader(modifier: Modifier = Modifier, version: String) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(100.dp),
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
    onSocialMediaItemClick: (Int) -> Unit
) {
    Card(modifier = modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            socialMediaLinks.forEach { socialMediaItem ->
                SocialMediaItem(
                    socialMediaItem = socialMediaItem,
                    onSocialMediaItemClick = onSocialMediaItemClick
                )
            }
        }
    }
}

@Composable
private fun InfoItem(modifier: Modifier = Modifier, devInfo: InfoItemVo, onClick: () -> Unit = {}) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = devInfo.iconRes),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = stringResource(id = devInfo.titleRes),
                style = MaterialTheme.typography.titleMedium
            )
            if (devInfo.subtitleRes != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = devInfo.subtitleRes),
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
    }
}

@Composable
fun SocialMediaItem(socialMediaItem: SocialMediaItemVo, onSocialMediaItemClick: (Int) -> Unit) {
    IconButton(

        onClick = { onSocialMediaItemClick(socialMediaItem.urlRes) }) {
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
        Column {
            InfoItem(
                devInfo = InfoItemVo(
                    R.string.info_dev, R.string.info_dev_subtitle, R.drawable.ic_mail
                )
            )
            InfoItem(
                devInfo = InfoItemVo(
                    titleRes = R.string.info_dev, iconRes = R.drawable.ic_terms
                )
            )
        }

    }
}
