package com.challenge.foodlover.feature.restaurantitem

import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class RestaurantItemViewStateTest {

    private val observeRestaurantFavoriteStatus =
        mockk<ObserveRestaurantFavoriteStatusUseCase>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var viewState: IRestaurantItemViewState

    @Before
    fun setup() {
        //Given
        viewState = RestaurantItemViewState(
            restaurant = mockedRestaurant,
            observeRestaurantFavoriteStatus = observeRestaurantFavoriteStatus
        )
    }

    @Test
    fun `when name is called Should return restaurant name`() {
        //When
        val result = viewState.name

        //Then
        assertEquals(mockedRestaurant.name, result)
    }

    @Test
    fun `when rating is called Should return restaurant ratingAverage as Float`() {
        //When
        val result = viewState.rating

        //Then
        assertEquals(mockedRestaurant.ratingAverage.toFloat(), result)
    }

    @Test
    fun `when status is called Should return restaurant status`() {
        //When
        val result = viewState.status

        //Then
        assertEquals(mockedRestaurant.status, result)
    }

    @Test
    fun `when isFavorite is called Should call observeRestaurantFavoriteStatus`() {
        //When
        viewState.isFavorite

        //Then
        verify { observeRestaurantFavoriteStatus.invoke(mockedRestaurant) }
    }
}