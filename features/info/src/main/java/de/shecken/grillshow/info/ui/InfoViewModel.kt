package de.shecken.grillshow.info.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.info.interactor.InfoInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class InfoViewModel(interactor: InfoInteractor) : ViewModel() {

    private val _versionName = interactor.versionName
    val versionName =
        _versionName.stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, "")
}