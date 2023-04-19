package de.shecken.grillshow.legal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.navigation.InfoRouter
import de.shecken.grillshow.navigation.legalType
import kotlinx.coroutines.flow.*

class LegalViewModel(savedStateHandle: SavedStateHandle, val router: InfoRouter) : ViewModel() {

    private val _url = MutableStateFlow(savedStateHandle[legalType] ?: "")
    val type = _url.map { LegalScreenType.valueOf(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, LegalScreenType.PRIVACY)

    fun onBackButtonClick() = router.goBack()
}