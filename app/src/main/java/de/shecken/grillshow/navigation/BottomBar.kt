package de.shecken.grillshow.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.R
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomBar(viewModel: BottomBarViewModel = getViewModel()) {

    val selectedItem by viewModel.selectedItem.collectAsStateWithLifecycle()
    BottomBar(
        selectedItem = selectedItem,
        onSelectionUpdate = viewModel::updateSelectedItem,
        onDashboardClick = viewModel::onDashboardClick,
        onFavoritesClick = viewModel::onFavoritesClick,
        onInfoClick = viewModel::onInfoClick
    )
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onSelectionUpdate: (Int) -> Unit,
    onDashboardClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    val navigationOptions = listOf(
        NavigationOption.Dashboard(onOptionClick = onDashboardClick),
        NavigationOption.Favorites(onOptionClick = onFavoritesClick),
        NavigationOption.Info(onOptionClick = onInfoClick)
    )


    NavigationBar(modifier = modifier) {
        navigationOptions.forEachIndexed { index, navigationOption ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onSelectionUpdate(index)
                    navigationOption.onOptionClick()
                },
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

sealed class NavigationOption(
    @StringRes val labelTextRes: Int,
    @DrawableRes val iconRes: Int,
    val onOptionClick: () -> Unit
) {
    class Dashboard(onOptionClick: () -> Unit) : NavigationOption(
        labelTextRes = R.string.navigation_dashboard,
        iconRes = R.drawable.ic_grill,
        onOptionClick = onOptionClick
    )

    class Favorites(onOptionClick: () -> Unit) : NavigationOption(
        labelTextRes = R.string.navigation_favorites,
        iconRes = R.drawable.ic_favorite,
        onOptionClick = onOptionClick
    )

    class Info(onOptionClick: () -> Unit) : NavigationOption(
        labelTextRes = R.string.navigation_info,
        iconRes = R.drawable.ic_more,
        onOptionClick = onOptionClick
    )
}

@Composable
@Preview
private fun BottomNavigationPreview() {
    GrillshowTheme() {
        BottomBar(
            selectedItem = 0,
            onSelectionUpdate = {},
            onDashboardClick = {},
            onFavoritesClick = {},
            onInfoClick = {})
    }
}