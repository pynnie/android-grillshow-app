package de.shecken.grillshow.shop.di

import de.shecken.grillshow.shop.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel() }
}
