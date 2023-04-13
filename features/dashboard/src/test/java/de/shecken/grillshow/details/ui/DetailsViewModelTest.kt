package de.shecken.grillshow.details.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import de.shecken.grillshow.DashboardRouter
import de.shecken.grillshow.dashboard.fakeDetailsVo1
import de.shecken.grillshow.dashboard.fakeRecipe1
import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.vo.RecipeDetailsVo
import de.shecken.grillshow.recipeId
import de.shecken.grillshow.sharedtest.coroutineTest
import de.shecken.grillshow.sharedtest.test
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

@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailsViewModelTest {

    private lateinit var underTest: DetailsViewModel

    private val detailsInteractorMock = mockk<DetailsInteractor>(relaxed = true)
    private val savedStateHandleMock = SavedStateHandle().apply { set(recipeId, fakeRecipe1.id) }
    private val detailsRouterMock = mockk<DashboardRouter>(relaxed = true)

    private val details = MutableStateFlow<RecipeDetailsVo?>(fakeDetailsVo1)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        every { detailsInteractorMock.getRecipeDetails(fakeRecipe1.id) } returns details
        underTest = DetailsViewModel(detailsInteractorMock, savedStateHandleMock, detailsRouterMock)
    }

    @Test
    fun onBackButtonClick() {
        // when
        underTest.onBackButtonClick()
        // then
        verify { detailsRouterMock.goBack() }
    }

    @Test
    fun onFavIconClick() = coroutineTest {
        // given
        val id = "123"
        val isFavorite = true
        // when
        underTest.onFavIconClick(id, isFavorite)
        // then
        coVerify { detailsInteractorMock.updateFavoriteProperty(id, isFavorite) }
    }

    @Test
    fun onShareIconClick() {
        // given
        val id = "4433"
        // when
        underTest.onShareIconClick(id)
        // then
        verify { detailsRouterMock.shareRecipe(id) }
    }

    @Test
    fun `null response should result in failure state`() = coroutineTest {
        // when
        details.value = null
        // then
        underTest.detailsScreenState.test(this) {
            assertValue(DetailsScreenState.Failure)
        }
        underTest.detailsScreenState.test {
            assertEquals(
                DetailsScreenState.Failure,
                awaitItem()
            )
        }
    }

    @Test
    fun `successful response should result in success state`() = coroutineTest {
        // given
        val fakeDetails = fakeDetailsVo1
        // when
        details.value = fakeDetails
        // then
        underTest.detailsScreenState.test {
            val actual = awaitItem()
            assertTrue(actual is DetailsScreenState.Success)
            assertEquals(fakeDetails, (actual as DetailsScreenState.Success).recipeDetails)
        }
    }
}