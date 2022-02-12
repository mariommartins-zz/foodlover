package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.foodlover.util.UnitTestDispatcherMap
import com.challenge.testcore.RestaurantFactory
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class RestaurantDetailsViewStateTest {

    private val dispatcherMap = UnitTestDispatcherMap()
    private val toggleRestaurantFavoriteStatus =
        mockk<ToggleRestaurantFavoriteStatusUseCase>(relaxed = true)

    private lateinit var viewState: RestaurantDetailsViewState

    @Before
    fun setup() {
        viewModel = RestaurantDetailsViewModel(
            dispatcherMap = dispatcherMap,
            restaurant = mockk(relaxed = true),
            observeRestaurantFavoriteStatus = mockk(relaxed = true),
            toggleRestaurantFavoriteStatus = toggleRestaurantFavoriteStatus,
            mutableState = mockk(relaxed = true)
        )
    }

    @Test
    fun `when toggleFavoriteStatus is called Should call toggleRestaurantFavoriteStatusUseCase`(){
        //When
        viewModel.toggleFavoriteStatus()

        //Then
        coVerify { toggleRestaurantFavoriteStatus.invoke(any()) }
    }
}