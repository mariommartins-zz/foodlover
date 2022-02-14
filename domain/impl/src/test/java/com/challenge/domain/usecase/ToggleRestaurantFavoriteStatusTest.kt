package com.challenge.domain.usecase

import com.challenge.domain.repository.IRestaurantRepository
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class ToggleRestaurantFavoriteStatusTest {

    private val repository = mockk<IRestaurantRepository>(relaxed = true)

    private lateinit var useCase: ToggleRestaurantFavoriteStatusUseCase

    @Before
    fun setup() {
        //Given
        useCase = ToggleRestaurantFavoriteStatus(repository = repository)
    }

    @Test
    fun `when useCase is called With favorited restaurant Should call removeFavorite from repository `() =
        runBlocking {
            //Given
            val restaurant = RestaurantFactory.make(isFavorite = true)

            //When
            useCase(restaurant)

            //Then
            coVerify { repository.removeFavorite(restaurant) }
        }

    @Test
    fun `when useCase is called With unfavorited restaurant Should call addFavorite from repository `() =
        runBlocking {
            //Given
            val restaurant = RestaurantFactory.make(isFavorite = false)

            //When
            useCase(restaurant)

            //Then
            coVerify { repository.addFavorite(restaurant) }
        }
}