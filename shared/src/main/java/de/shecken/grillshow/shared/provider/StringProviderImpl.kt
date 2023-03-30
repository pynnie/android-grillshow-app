package de.shecken.grillshow.shared.provider

import android.content.Context

class StringProviderImpl(private val context: Context) : StringProvider {

    override fun provideString(stringResId: Int): String = context.getString(stringResId)
}