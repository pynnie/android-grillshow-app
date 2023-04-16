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
import de.shecken.grillshow.shared.GrillshowTheme

import org.koin.androidx.compose.getViewModel


@Composable
fun InfoScreen(vIewModel: InfoViewModel = getViewModel()) {

    val version by vIewModel.versionName.collectAsStateWithLifecycle()
    InfoScreen(version = version)
}

@Composable
private fun InfoScreen(version: String) {
    Scaffold(
        topBar = { InfoTopBar() }) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            InfoScreenContent(version = version)
        }
    }

}

@Composable
private fun InfoTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.info_title)) }
    )
}

@Composable
private fun InfoScreenContent(version: String) {
    Column(Modifier.fillMaxWidth()) {
        InfoHeader(version = version)

        Divider()
    }
}

@Composable
private fun InfoHeader(modifier: Modifier = Modifier, version: String) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.info_version, version))

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
@Preview
private fun InfoHeaderPreview() {
    GrillshowTheme {
        InfoHeader(version = "1.0")
    }
}