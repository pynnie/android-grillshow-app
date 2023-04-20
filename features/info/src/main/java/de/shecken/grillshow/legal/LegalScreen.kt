@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.legal


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import de.shecken.grillshow.shared.ui.BackButton
import org.koin.androidx.compose.getViewModel

@Composable
fun LegalScreen(viewModel: LegalViewModel = getViewModel()) {

    val type by viewModel.type.collectAsStateWithLifecycle()
    LegalScreen(type = type, onBackButtonClick = viewModel::onBackButtonClick)
}


@Composable
private fun LegalScreen(type: LegalScreenType, onBackButtonClick: () -> Unit) {
    Scaffold(topBar = {
        LegalTopBar(
            titleRes = type.titleRes,
            onBackButtonClick = onBackButtonClick
        )
    }) { padding ->
        WebView(modifier = Modifier.padding(padding), state = rememberWebViewState(url = type.url))
    }
}

@Composable
private fun LegalTopBar(@StringRes titleRes: Int, onBackButtonClick: () -> Unit) {
    TopAppBar(title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            BackButton(onClick = onBackButtonClick)
        })
}
