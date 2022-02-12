package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.testcore.dispatcher.UnitTestDispatcherMap
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

internal class RestaurantDetailsViewModelTest {

    private val dispatcherMap = UnitTestDispatcherMap()
    private val toggleRestaurantFavoriteStatus =
        mockk<ToggleRestaurantFavoriteStatusUseCase>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var viewModel: IRestaurantDetailsViewAction

    @Before
    fun setup() {
        //Given
        viewModel = RestaurantDetailsViewModel(
            dispatcherMap = dispatcherMap,
            restaurant = mockedRestaurant,
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
}