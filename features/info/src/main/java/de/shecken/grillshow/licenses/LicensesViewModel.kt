package de.shecken.grillshow.licenses

import androidx.lifecycle.ViewModel
import de.shecken.grillshow.info.navigation.InfoRouter

class LicensesViewModel(private val router: InfoRouter) : ViewModel() {

    fun onBackButtonClicked() = router.goBack()
}