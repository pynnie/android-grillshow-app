package de.shecken.grillshow.shared.flow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.Int.Companion.MAX_VALUE

/**
 * A [MutableSharedFlow] that can be used to send events from ViewModels to the UI.
 */
@Suppress("FunctionName")
fun <T> EventFlow() = MutableSharedFlow<T>(extraBufferCapacity = MAX_VALUE)

/**
 * Sends an event through a [MutableSharedFlow] (i.e. an [EventFlow]).
 */
fun <T> MutableSharedFlow<T>.send(t: T) {
    tryEmit(t)
}

/**
 * Safely collects a flow within a [Composable].
 *
 * Based on article "A safer way to collect flows from Android UIs":
 * https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
 */
@Composable
inline fun <reified T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collect { action(it) }
        }
    }
}
