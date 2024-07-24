package de.shecken.grillshow.shared.di

import de.shecken.grillshow.shared.provider.StringProvider
import de.shecken.grillshow.shared.provider.StringProviderImpl
import org.koin.dsl.module

val sharedModule = module {
    single<StringProvider> { StringProviderImpl(get()) }
}