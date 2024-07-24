@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.licenses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import de.shecken.grillshow.info.R
import de.shecken.grillshow.shared.ui.BackButton
import org.koin.androidx.compose.getViewModel

@Composable
fun LicensesScreen(viewModel: LicensesViewModel = getViewModel()) {
    LicensesScreen(onBackButtonClick = viewModel::onBackButtonClicked)
}

@Composable
private fun LicensesScreen(onBackButtonClick: () -> Unit) {
    Scaffold(
        topBar = {
            LicensesTopAppBar(onBackButtonClick = onBackButtonClick)
        }
    ) { padding ->
        Column {
            LibrariesContainer(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                colors = grillshowLibraryColors()
            )

        }
    }
}

@Composable
private fun LicensesTopAppBar(onBackButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.info_licenses)) },
        navigationIcon = {
            BackButton(onClick = onBackButtonClick)
        }
    )
}

@Composable
private fun grillshowLibraryColors() = with(MaterialTheme.colorScheme) {
    return@with LibraryDefaults.libraryColors(
        backgroundColor = background,
        contentColor = onBackground,
        badgeBackgroundColor = primaryContainer,
        badgeContentColor = onPrimaryContainer
    )
}