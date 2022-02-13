package com.challenge.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.challenge.domain.repository.IRestaurantRepository
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

internal class ObserveRestaurantFavoriteStatusTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<IRestaurantRepository>(relaxed = true)
    private val favoriteStatusObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var useCase: ObserveRestaurantFavoriteStatusUseCase

    @Before
    fun setup() {
        //Given
        useCase = ObserveRestaurantFavoriteStatus(repository = repository)
    }

    @Test
    fun `when useCase is called Should call observeFavoriteStatusBy from repository and Return correct observable`() {
        //Given
        every {
            repository.observeFavoriteStatusBy(mockedRestaurant.name)
        } returns MutableLiveData(true)

        //When
        useCase(mockedRestaurant).observeForever(favoriteStatusObserver)

        //Then
        verify { repository.observeFavoriteStatusBy(mockedRestaurant.name) }
        verify { favoriteStatusObserver.onChanged(true) }
        assertTrue(mockedRestaurant.isFavorite)
    }
}