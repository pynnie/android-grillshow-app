package de.shecken.grillshow.licenses

import androidx.lifecycle.ViewModel
import de.shecken.grillshow.navigation.InfoRouter

class LicensesViewModel(private val router: InfoRouter) : ViewModel() {

    fun onBackButtonClicked() = router.goBack()
}