package de.shecken.grillshow.dashboard.interactor

import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.repository.recipe.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DashboardInteractorImplTest {

    private lateinit var underTest: DashboardInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxed = true)

    @BeforeEach
    fun setUp() {
        underTest = DashboardInteractorImpl(recipeRepositoryMock)
    }

    @Test
    fun getCategoriesWithRecipes() = runTest {
        // when
        underTest.getCategoriesWithRecipes()
        // then
        coVerify { recipeRepositoryMock.categories }
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
}