package de.shecken.grillshow.shop.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchInteractorImplTest {

    private lateinit var underTest: SearchInteractorImpl

    private val recipeRepositoryMock = mockk<RecipeRepository>(relaxUnitFun = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        coEvery { recipeRepositoryMock.searchRecipes(any()) } returns flowOf(emptyList())
        underTest = SearchInteractorImpl(recipeRepositoryMock)
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