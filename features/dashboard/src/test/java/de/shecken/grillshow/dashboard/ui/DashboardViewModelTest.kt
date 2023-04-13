package de.shecken.grillshow.dashboard.ui

import app.cash.turbine.test
import de.shecken.grillshow.dashboard.fakeCategory1
import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.DashboardRouter
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.sharedtest.coroutineTest
import de.shecken.grillshow.sharedtest.test
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val categories = MutableStateFlow(emptyList<Category>())

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
        val fakeRecipe = fakeRecipe1
        // when
        underTest.onRecipeClick(fakeRecipe)
        // then
        verify { dashboardRouterMock.openRecipeDetails(fakeRecipe.id) }
    }

    @Test
    fun `empty category list should result in failure state`() = coroutineTest {
        // when
        categories.value = emptyList()
        // then
        underTest.dashboardScreenState.test(this) {
            assertValue(DashboardSceenState.Failure)
        }
        underTest.dashboardScreenState.test {
            assertEquals(
                DashboardSceenState.Failure,
                awaitItem()
            )
        }
    }

    @Test
    fun `filled category list should result in success state`() = coroutineTest {
        // given
        val fakeCategories = listOf(fakeCategory1)
        // when
        categories.value = fakeCategories
        // then
        underTest.dashboardScreenState.test {
            val actual = awaitItem()
            assertTrue(actual is DashboardSceenState.Success)
            assertEquals(
                fakeCategories, (actual as DashboardSceenState.Success).categories
            )
        }
    }
}