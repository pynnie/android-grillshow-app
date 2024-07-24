@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package de.shecken.grillshow.category.interactor

import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CategoryInteractorImplTest {

    private lateinit var underTest: CategoryInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxed = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        underTest = CategoryInteractorImpl(recipeRepositoryMock)
    }

    @Test
    fun loadCategory() {
        // given
        val id = "123"
        // when
        underTest.loadCategory(id)
        // then
        verify { recipeRepositoryMock.getCategoryById(id) }
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
    fun updateCategories() = coroutineTest {
        // when
        underTest.updateCategories()
        // then
        coVerify { recipeRepositoryMock.fetchCategories() }
    }
}