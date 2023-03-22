package de.shecken.grillshow.sharedtest

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class TestFlowObserver<T> internal constructor(
    scope: CoroutineScope,
    flow: Flow<T>
) {

    private val values = mutableListOf<T>()

    private val job = flow.onEach(values::add).launchIn(scope)

    private val value: T get() = values.last()

    /**
     * Asserts that no value is emitted from the [Flow].
     */
    fun assertNoValue(): TestFlowObserver<T> {
        values.shouldBeEmpty()
        return this
    }

    /**
     * Asserts that at least one value is emitted from the [Flow].
     */
    fun assertHasValue(): TestFlowObserver<T> {
        values.shouldNotBeEmpty()
        return this
    }

    /**
     * Asserts that the [expected] value is emitted from the [Flow] last.
     */
    fun assertValue(expected: T): TestFlowObserver<T> {
        values.last().shouldBe(expected)
        return this
    }

    /**
     * Asserts that the last value emitted from the [Flow] matches the passed [valuePredicate].
     */
    fun assertValue(valuePredicate: (value: T?) -> Boolean): TestFlowObserver<T> {
        val value = values.last()
        if (!valuePredicate(value)) {
            throw AssertionError("Value $value does not match the predicate $valuePredicate.")
        }
        return this
    }

    /**
     * Asserts that the [expected] value is emitted from the [Flow] at the passed [index].
     */
    fun assertValueAt(index: Int, expected: T): TestFlowObserver<T> {
        values[index] shouldBe expected
        return this
    }

    /**
     * Asserts that the value that is emitted from the [Flow] at the passed [index] matches the passed [valuePredicate].
     */
    fun assertValueAt(index: Int, valuePredicate: (value: T?) -> Boolean): TestFlowObserver<T> {
        val value = values[index]
        if (!valuePredicate(value)) {
            throw AssertionError("Value $value does not match the predicate $valuePredicate.")
        }
        return this
    }

    /**
     * Finishes the test of this [Flow].
     */
    fun finish() {
        job.cancel()
    }
}
