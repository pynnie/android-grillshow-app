@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomNavigationScreen(
    viewModel: BottomBarViewModel = getViewModel(),
    screenContent: @Composable () -> Unit
) {
    val screenId by viewModel.contentScreenId.collectAsStateWithLifecycle()
    val showBottombar by viewModel.show.collectAsStateWithLifecycle()

    BottomNavigationScreen(
        contentScreenId = screenId,
        show = showBottombar,
        onNavigationItemClick = viewModel::navigateTo,
        screenContent = screenContent
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun BottomNavigationScreen(
    contentScreenId: ContentScreenId,
    show: Boolean,
    onNavigationItemClick: (ContentScreenId) -> Unit,
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                contentScreenId = contentScreenId,
                show = show,
                onNavigationItemClick = onNavigationItemClick
            )
        }
    ) {
        screenContent()
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    show: Boolean,
    contentScreenId: ContentScreenId,
    onNavigationItemClick: (ContentScreenId) -> Unit
) {
    if (!show) {
        return
    }
    NavigationBar(modifier = modifier) {
        ContentScreenId.values().forEach { navigationOption ->
            NavigationBarItem(
                selected = navigationOption == contentScreenId,
                onClick = { onNavigationItemClick(navigationOption) },
                icon = {
                    Image(
                        painter = painterResource(id = navigationOption.iconRes),
                        contentDescription = ""
                    )
                },
                label = { Text(text = stringResource(id = navigationOption.labelTextRes)) }
            )
        }
    }
}
