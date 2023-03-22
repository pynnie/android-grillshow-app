package de.shecken.grillshow.sharedtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.coroutines.CoroutineContext

/**
 * Function can be used to test suspend functions in UnitTests.
 */
@ExperimentalCoroutinesApi
fun coroutineTest(
    context: CoroutineContext = UnconfinedTestDispatcher(),
    testBody: suspend TestScope.() -> Unit
) = runTest(context = context, testBody = testBody)

/**
 * Function can be used at setup of UnitTests to set the main dispatcher.
 */
@ExperimentalCoroutinesApi
fun coroutineSetup(setupBody: () -> Unit) {
    Dispatchers.setMain(UnconfinedTestDispatcher())
    setupBody()
}
