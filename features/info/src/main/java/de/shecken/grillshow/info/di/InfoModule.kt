package de.shecken.grillshow.info.di

import de.shecken.grillshow.info.ui.InfoVIewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val infoModule = module {
    viewModel { InfoVIewModel() }
}
