@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package de.shecken.grillshow.category

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import de.shecken.grillshow.category.interactor.CategoryInteractor
import de.shecken.grillshow.dashboard.fakeCategory1
import de.shecken.grillshow.dashboard.fakeCategoryVo1
import de.shecken.grillshow.navigation.DashboardRouter
import de.shecken.grillshow.navigation.categoryId
import de.shecken.grillshow.sharedtest.coroutineTest
import de.shecken.grillshow.vo.CategoryVo
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CategoryViewModelTest {

    private lateinit var underTest: CategoryViewModel

    private val routerMock = mockk<DashboardRouter>(relaxed = true)
    private val interactorMock = mockk<CategoryInteractor>(relaxed = true)
    private val savedStateHandleMock =
        SavedStateHandle().apply { set(categoryId, fakeCategory1.id) }

    private val category = MutableStateFlow<CategoryVo?>(fakeCategoryVo1)


    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        every { interactorMock.loadCategory(fakeCategory1.id) } returns category
        underTest = CategoryViewModel(routerMock, interactorMock, savedStateHandleMock)
    }

    @Test
    fun onReloadButtonClick() = coroutineTest {
        // when
        underTest.onReloadButtonClick()
        // then
        coVerify { interactorMock.updateCategories() }
    }

    @Test
    fun onRecipeClick() {
        // given
        val id = "456"
        // when
        underTest.onRecipeClick(id)
        // then
        verify { routerMock.openRecipeDetails(id) }
    }

    @Test
    fun onFavIconClick() = coroutineTest {
        // given
        val id = "123"
        val isFavorite = true
        // when
        underTest.onFavIconClick(id, isFavorite)
        // then
        coVerify { interactorMock.updateFavoriteProperty(id, isFavorite) }
    }

    @Test
    fun goBack() {
        // when
        underTest.goBack()
        // then
        verify { routerMock.goBack() }
    }

    @Test
    fun `null response should result in failure state`() = coroutineTest {
        // when
        category.value = null
        // then
        underTest.screenState.test {
            assert(awaitItem() is CategoryScreenState.Failure)
        }
    }

    @Test
    fun `category with empty recipe list should result in failure state`() = coroutineTest {
        // given
        val categoryWithoutRecipes = fakeCategoryVo1.copy(recipes = emptyList())
        // when
        category.value = categoryWithoutRecipes
        // then
        underTest.screenState.test {
            assert(awaitItem() is CategoryScreenState.Failure)
        }
    }

    @Test
    fun `successful response should result in success state`() = coroutineTest {
        // given
        val fakeCat = fakeCategoryVo1
        // when
        category.value = fakeCat
        // then
        underTest.screenState.test {
            val actual = awaitItem()
            assertTrue(actual is CategoryScreenState.Success)
            assertEquals(fakeCat, (actual as CategoryScreenState.Success).category)
        }
    }
}