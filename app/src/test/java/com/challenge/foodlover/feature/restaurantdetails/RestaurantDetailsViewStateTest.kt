package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.common.android.extensions.asMoney
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class RestaurantDetailsViewStateTest {

    private val observeRestaurantFavoriteStatus =
        mockk<ObserveRestaurantFavoriteStatusUseCase>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var viewState: IRestaurantDetailsViewState

    @Before
    fun setup() {
        //Given
        viewState = RestaurantDetailsViewState(
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
    fun `when status is called Should return restaurant status`() {
        //When
        val result = viewState.status

        //Then
        assertEquals(mockedRestaurant.status, result)
    }

    @Test
    fun `when bestMatch is called Should return restaurant bestMatch as String`() {
        //When
        val result = viewState.bestMatch

        //Then
        assertEquals(mockedRestaurant.bestMatch.toString(), result)
    }

    @Test
    fun `when newest is called Should return restaurant newest as String`() {
        //When
        val result = viewState.newest

        //Then
        assertEquals(mockedRestaurant.newest.toString(), result)
    }

    @Test
    fun `when ratingAverage is called Should return restaurant ratingAverage as String`() {
        //When
        val result = viewState.ratingAverage

        //Then
        assertEquals(mockedRestaurant.ratingAverage.toString(), result)
    }

    @Test
    fun `when distance is called Should return restaurant distance as String`() {
        //When
        val result = viewState.distance

        //Then
        assertEquals(mockedRestaurant.distance, result)
    }

    @Test
    fun `when popularity is called Should return restaurant popularity as String`() {
        //When
        val result = viewState.popularity

        //Then
        assertEquals(mockedRestaurant.popularity.toString(), result)
    }

    @Test
    fun `when averageProductPrice is called Should return restaurant averageProductPrice as String`() {
        //When
        val expected = mockedRestaurant.averageProductPrice.asMoney()
        val result = viewState.averageProductPrice

        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `when deliveryCosts is called Should return restaurant deliveryCosts as String`() {
        //When
        val expected = mockedRestaurant.deliveryCosts.asMoney()
        val result = viewState.deliveryCosts

        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `when minCost is called Should return restaurant minCost as String`() {
        //When
        val expected = mockedRestaurant.minCost.asMoney()
        val result = viewState.minCost

        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `when isFavorite is called Should call observeRestaurantFavoriteStatus`() {
        //When
        viewState.isFavorite

        //Then
        verify { observeRestaurantFavoriteStatus(mockedRestaurant) }
    }
}