package de.shecken.grillshow.shop.ui

import app.cash.turbine.test
import de.shecken.grillshow.sharedtest.coroutineTest
import de.shecken.grillshow.shop.interactor.SearchInteractor
import de.shecken.grillshow.shop.navigation.SearchRouter
import de.shecken.grillshow.shop.vo.SearchResultVo
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchViewModelTest {

    private lateinit var underTest: SearchViewModel

    private val searchInteractorMock = mockk<SearchInteractor>(relaxed = true)
    private val searchRouterMock = mockk<SearchRouter>(relaxed = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        underTest = SearchViewModel(searchInteractorMock, searchRouterMock)
    }

    @Test
    fun onQueryChange() = coroutineTest {
        // given
        val query = "test"
        // when
        underTest.onQueryChange(query)
        // then
        verify { searchInteractorMock.searchForRecipes(query) }
    }

    @Test
    fun `empty query should result in empty state`() = coroutineTest {
        // given
        val emptyQuery = ""
        // when
        underTest.onQueryChange(emptyQuery)
        // then
        underTest.searchScreenState.test {
            val actual = awaitItem()
            assert(actual is SearchScreenState.Empty)
        }
    }

    @Test
    fun `empty search result list should result in empty state`() = coroutineTest {
        // given
        val query = "abc"
        coEvery { searchInteractorMock.searchForRecipes(query) } returns flowOf(emptyList())
        // when
        underTest.onQueryChange(query)
        // then
        underTest.searchScreenState.test {
            val actual = awaitItem()
            assert(actual is SearchScreenState.Empty)
        }
    }

    @Test
    fun `filled search result list should result in success state`() = coroutineTest {
        // given
        val query = "abc"
        val results = listOf(
            SearchResultVo("1", "test", "test")
        )
        coEvery { searchInteractorMock.searchForRecipes(query) } returns flowOf(results)
        // when
        underTest.onQueryChange(query)
        // then
        underTest.searchScreenState.test {
            val actual = awaitItem()
            assert(actual is SearchScreenState.Success)
            assertEquals((actual as SearchScreenState.Success).recipes, results)
        }
    }
}