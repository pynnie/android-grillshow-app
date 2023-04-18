package de.shecken.grillshow.dashboard.ui

import app.cash.turbine.test
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.DashboardRouter
import de.shecken.grillshow.dashboard.fakeCategoryVo1
import de.shecken.grillshow.dashboard.fakeRecipeListItemVo1
import de.shecken.grillshow.dashboard.ui.DashboardSceenState.SearchScreenState
import de.shecken.grillshow.dashboard.vo.CategoryVo
import de.shecken.grillshow.dashboard.vo.SearchResultVo
import de.shecken.grillshow.sharedtest.coroutineTest
import de.shecken.grillshow.sharedtest.test
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DashboardViewModelTest {

    private lateinit var underTest: DashboardViewModel

    private val dashboardInteractorMock = mockk<DashboardInteractor>(relaxed = true)
    private val dashboardRouterMock = mockk<DashboardRouter>(relaxed = true)

    private val categories = MutableStateFlow(emptyList<CategoryVo>())

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        coEvery { dashboardInteractorMock.getCategoriesWithRecipes() } returns categories
        underTest = DashboardViewModel(dashboardRouterMock, dashboardInteractorMock)
    }

    @Test
    fun onFavIconClick() = runTest {
        // given
        val id = "123"
        val isFavorite = true
        // when
        underTest.onFavIconClick(id, isFavorite)
        // then
        coVerify { dashboardInteractorMock.updateFavoriteProperty(id, isFavorite) }
    }

    @Test
    fun onRecipeClick() {
        // given
        val fakeRecipe = fakeRecipeListItemVo1
        // when
        underTest.onRecipeClick(fakeRecipe.id)
        // then
        verify { dashboardRouterMock.openRecipeDetails(fakeRecipe.id) }
    }

    @Test
    fun `empty category list should result in failure state`() = coroutineTest {
        // when
        categories.value = emptyList()
        // then
        underTest.screenState.test(this) {
            assertValue(DashboardSceenState.Failure)
        }
        underTest.screenState.test {
            assertEquals(
                DashboardSceenState.Failure,
                awaitItem()
            )
        }
    }

    @Test
    fun `filled category list should result in success state`() = coroutineTest {
        // given
        val fakeCategories = listOf(fakeCategoryVo1)
        // when
        categories.value = fakeCategories
        // then
        underTest.screenState.test {
            val actual = awaitItem()
            assertTrue(actual is DashboardSceenState.Success)
            assertEquals(
                fakeCategories, (actual as DashboardSceenState.Success).categories
            )
        }
    }

    @Test
    fun onQueryChange() = coroutineTest {
        // given
        val query = "test"
        // when
        underTest.onQueryChange(query)
        // then
        verify { dashboardInteractorMock.searchForRecipes(query) }
    }

    @Test
    fun `empty query should result in empty state`() = coroutineTest {
        // given
        val emptyQuery = ""
        underTest.toggleSearchMode(true)
        // when
        underTest.onQueryChange(emptyQuery)
        // then
        underTest.screenState.test {
            val actual = awaitItem()
            assert(actual is SearchScreenState.Empty)
        }
    }

    @Test
    fun `empty search result list should result in empty state`() = coroutineTest {
        // given
        val query = "abc"
        underTest.toggleSearchMode(true)
        coEvery { dashboardInteractorMock.searchForRecipes(query) } returns flowOf(emptyList())
        // when
        underTest.onQueryChange(query)
        // then
        underTest.screenState.test {
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
        underTest.toggleSearchMode(true)
        coEvery { dashboardInteractorMock.searchForRecipes(query) } returns flowOf(results)
        // when
        underTest.onQueryChange(query)
        // then
        underTest.screenState.test {
            val actual = awaitItem()
            assert(actual is SearchScreenState.Success)
            assertEquals((actual as SearchScreenState.Success).recipes, results)
        }
    }

    @Test
    fun `state should not be SearchState when not in search mode`() = coroutineTest {
        // given
        val query = "abc"
        val results = listOf(
            SearchResultVo("1", "test", "test")
        )
        underTest.toggleSearchMode(false)
        coEvery { dashboardInteractorMock.searchForRecipes(query) } returns flowOf(results)
        // when
        underTest.onQueryChange(query)
        // then
        underTest.screenState.test {
            val actual = awaitItem()
            assert(actual !is SearchScreenState)
        }
    }
}