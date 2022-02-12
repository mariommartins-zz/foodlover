package com.challenge.foodlover.feature.restaurantitem

import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.testcore.RestaurantFactory
import com.challenge.testcore.dispatcher.UnitTestDispatcherMap
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

internal class RestaurantItemViewModelTest {

    private val dispatcherMap = UnitTestDispatcherMap()
    private val toggleRestaurantFavoriteStatus =
        mockk<ToggleRestaurantFavoriteStatusUseCase>(relaxed = true)
    private val onItemClicked = mockk<(Restaurant) -> Unit>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var viewModel: IRestaurantItemViewAction

    @Before
    fun setup() {
        //Given
        viewModel = RestaurantItemViewModel(
            dispatcherMap = dispatcherMap,
            restaurant = mockedRestaurant,
            onItemClicked = onItemClicked,
            observeRestaurantFavoriteStatus = mockk(relaxed = true),
            toggleRestaurantFavoriteStatus = toggleRestaurantFavoriteStatus,
            mutableState = mockk(relaxed = true)
        )
    }

    @Test
    fun `when toggleFavoriteStatus is called Should call toggleRestaurantFavoriteStatusUseCase`() {
        //When
        viewModel.toggleFavoriteStatus()

        //Then
        coVerify { toggleRestaurantFavoriteStatus(mockedRestaurant) }
    }

    @Test
    fun `when onCardClicked is called Should call onCardClicked callback`() {
        //When
        viewModel.onCardClicked()

        //Then
        coVerify { onItemClicked(mockedRestaurant) }
    }
}