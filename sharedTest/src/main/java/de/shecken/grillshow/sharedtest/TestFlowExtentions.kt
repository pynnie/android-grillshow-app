package de.shecken.grillshow.sharedtest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Extension function to be used to safely test the emission of a [Flow]. The used Job is automatically cancelled at the end of the
 * [assertActions] lambda. See [TestFlowObserver] for assertion functions.
 */
fun <T> Flow<T>.test(scope: CoroutineScope, assertActions: TestFlowObserver<T>.() -> Unit) = TestFlowObserver(scope, this).run {
    assertActions(this)
    finish()
}

/**
 * Extension function to be used to test the emission of a [Flow]. See [TestFlowObserver] for assertion functions. After all assertions,
 * the Job should be finished with [TestFlowObserver.finish].
 */
fun <T> Flow<T>.test(scope: CoroutineScope) = TestFlowObserver(scope, this)
