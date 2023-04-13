package de.shecken.grillshow.details.interactor

import app.cash.turbine.test
import de.shecken.grillshow.dashboard.fakeDetailsVo1
import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.details.utils.IngredientExtractor
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailsInteractorImplTest {

    private lateinit var underTest: DetailsInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxed = true)
    private val ingredientExtractorMock = mockk<IngredientExtractor>(relaxed = true)

    private val details = MutableStateFlow<Recipe?>(null)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        coEvery { recipeRepositoryMock.getRecipeForIdAsFlow(any()) } returns details
        underTest = DetailsInteractorImpl(recipeRepositoryMock, ingredientExtractorMock)
    }

    @Test
    fun getRecipeDetails() = coroutineTest {
        // given
        val fakeRecipe = fakeRecipe1
        val expected = fakeDetailsVo1
        every { ingredientExtractorMock.extractIngredientsFromText(fakeRecipe.description) } returns expected.ingredientlist
        // when
        details.value = fakeRecipe
        val result = underTest.getRecipeDetails(fakeRecipe.id)
        // then
        coVerify { recipeRepositoryMock.getRecipeForIdAsFlow(fakeRecipe.id) }
        result.test {
            val actual = awaitItem()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun updateFavoriteProperty() = coroutineTest { // given
        val fakeRecipe = fakeRecipe1
        coEvery { recipeRepositoryMock.getRecipeForId(fakeRecipe.id) } returns fakeRecipe
        // when
        underTest.updateFavoriteProperty(fakeRecipe.id, true)
        // then
        coVerify { recipeRepositoryMock.getRecipeForId(fakeRecipe.id) }
        coVerify { recipeRepositoryMock.updateRecipe(fakeRecipe.copy(isFavorite = true)) }
    }
}