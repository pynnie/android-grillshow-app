package de.shecken.grillshow.shared.ui.navigation

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
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.R
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomBar(viewModel: BottomBarViewModel = getViewModel()) {
    BottomBar(
        onDashboardClick = viewModel::onDashboardClick,
        onSearchClick = viewModel::onSearchClick,
        onFavoritesClick = viewModel::onFavoritesClick)
}
@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    onDashboardClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    val navigationOptions = listOf(
        NavigationOption.Dashboard(onOptionClick = onDashboardClick),
        NavigationOption.Search(onOptionClick = onSearchClick),
        NavigationOption.Favorites(onOptionClick = onFavoritesClick)
    )
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(modifier = modifier) {
        navigationOptions.forEachIndexed { index, navigationOption ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
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

    class Search(onOptionClick: () -> Unit) : NavigationOption(
        labelTextRes = R.string.navigation_search,
        iconRes = R.drawable.ic_search,
        onOptionClick = onOptionClick
    )

    class Favorites(onOptionClick: () -> Unit) : NavigationOption(
        labelTextRes = R.string.navigation_favorites,
        iconRes = R.drawable.ic_favorite,
        onOptionClick = onOptionClick
    )
}

@Composable
@Preview
private fun BottomNavigationPreview() {
    GrillshowTheme() {
        BottomBar(onDashboardClick = {}, onSearchClick = {}, onFavoritesClick = {})
    }
}