package de.shecken.favorites.ui

import app.cash.turbine.test
import de.shecken.favorites.interactor.FavoritesInteractor
import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.favorites.vo.FavoriteVo
import de.shecken.grillshow.sharedtest.coroutineTest
import io.mockk.coEvery
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

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesViewModelTest {

    private lateinit var underTest: FavoritesViewModel

    private val favoritesRouterMock: FavoritesRouter = mockk(relaxUnitFun = true)
    private val interactorMock: FavoritesInteractor = mockk(relaxUnitFun = true)

    private val favoriteList = MutableStateFlow(emptyList<FavoriteVo>())

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        coEvery { interactorMock.getFavoriteRecipes() } returns favoriteList
        underTest = FavoritesViewModel(favoritesRouterMock, interactorMock)
    }

    @Test
    fun onItemClick() {
        // given
        val recipeId = "123"

        // when
        underTest.onItemClick(recipeId)

        // then
        verify { favoritesRouterMock.openRecipeDetails(recipeId) }
    }

    @Test
    fun `empty favorite list should result in empty state`() = coroutineTest {
        // when
        favoriteList.value = emptyList()
        // then
        underTest.favoritesScreenState.test {
            val actual = awaitItem()
            assert(actual is FavoritesScreenState.Empty)
        }
    }

    @Test
    fun `filled favorite list should result in success state`() = coroutineTest {
        // given
        val favorites = listOf(FavoriteVo("123", "title", "imageUrl"))
        // when
        favoriteList.value = favorites
        // then
        underTest.favoritesScreenState.test {
            val actual = awaitItem()
            assert(actual is FavoritesScreenState.Success)
            assertEquals(favorites, (actual as FavoritesScreenState.Success).favoriteList)
        }
    }
}