package de.shecken.favorites.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesInteractorImplTest {

    private lateinit var underTest: FavoritesInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxed = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        underTest = FavoritesInteractorImpl(recipeRepositoryMock)
    }

    @Test
    fun getFavoriteRecipes() = coroutineTest {
        // when
        underTest.getFavoriteRecipes()

        // then
        coVerify { recipeRepositoryMock.getAllFavorites() }
    }
}