package de.shecken.grillshow.dashboard.interactor

import app.cash.turbine.test
import de.shecken.grillshow.dashboard.fakeCategory1
import de.shecken.grillshow.dashboard.fakeCategoryVo1
import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DashboardInteractorImplTest {

    private lateinit var underTest: DashboardInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxed = true)

    private val categories = MutableStateFlow(emptyList<Category>())

    @BeforeEach
    fun setUp() {
        underTest = DashboardInteractorImpl(recipeRepositoryMock)
    }

    @Test
    fun getCategoriesWithRecipes() = runTest {
        // given
        val expected = listOf(fakeCategoryVo1)
        coEvery { recipeRepositoryMock.categories } returns categories
        // when
        categories.value = listOf(fakeCategory1)
        val result = underTest.getCategoriesWithRecipes()
        // then
        coVerify { recipeRepositoryMock.categories }
        result.test {
            val actual = awaitItem()
            assertEquals(actual, expected)
        }
    }

    @Test
    fun updateFavoriteProperty() = runTest {
        // given
        val fakeRecipe = fakeRecipe1
        coEvery { recipeRepositoryMock.getRecipeForId(any()) } returns fakeRecipe

        // when
        underTest.updateFavoriteProperty(fakeRecipe.id, true)
        // then
        coVerify { recipeRepositoryMock.getRecipeForId(fakeRecipe.id) }
        coVerify { recipeRepositoryMock.updateRecipe(fakeRecipe.copy(isFavorite = true)) }
    }

    @Test
    fun searchForRecipes() = coroutineTest {
        // given
        val query = "test"
        // when
        underTest.searchForRecipes(query)
        // then
        coVerify { recipeRepositoryMock.searchRecipes(query) }
    }
}