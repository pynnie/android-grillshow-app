package de.shecken.grillshow.shared.di

import de.shecken.grillshow.shared.ui.navigation.BottomBarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    viewModel { BottomBarViewModel(get()) }
}