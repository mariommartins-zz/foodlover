package com.challenge.foodlover.feature.restaurantlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.domain.model.Restaurant
import com.challenge.testcore.RestaurantFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RestaurantListViewStateTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val restaurantObserver = mockk<Observer<List<Restaurant>>>(relaxed = true)
    private val mockedRestaurants = RestaurantFactory.makeList()

    private lateinit var viewState: RestaurantListViewState

    @Before
    fun setup() {
        //Given
        viewState = RestaurantListViewState()
        viewState.restaurants.observeForever(restaurantObserver)
    }

    @Test
    fun `when postRestaurants is called Should postValue on _restaurants`() {
        //When
        viewState.postRestaurants(mockedRestaurants)

        //Then
        verify { restaurantObserver.onChanged(mockedRestaurants) }
    }
}
