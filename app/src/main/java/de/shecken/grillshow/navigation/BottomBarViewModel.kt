package de.shecken.grillshow.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BottomBarViewModel(private val router: BottomBarRouter) : ViewModel() {

    private var _selectedItem = MutableStateFlow(0)
    val selectedItem = _selectedItem.asStateFlow()

    fun updateSelectedItem(newSelectedItem: Int) = _selectedItem.update { newSelectedItem }

    fun onDashboardClick() = router.openDashboard()

    fun onFavoritesClick() = router.openFavorites()

    fun onInfoClick() = router.openInfo()
}