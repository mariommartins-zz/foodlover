package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.foodlover.util.rule.FragmentTestRule
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal class RestaurantDetailsDialogFragmentTest {

    private val toggleRestaurantFavoriteStatus =
        mockk<ToggleRestaurantFavoriteStatusUseCase>(relaxed = true)
    private val mockedRestaurant = RestaurantFactory.make()

    @get:Rule
    val fragmentTestRule = FragmentTestRule(RestaurantDetailsDialogFragment::class.java)

    init {
        fragmentTestRule.apply {
            beforeAction {
                module(override = true) {
                    single { toggleRestaurantFavoriteStatus }
                }.also { loadKoinModules(it) }
            }
            setupBundle { bundle -> bundle.putParcelable("restaurant", mockedRestaurant) }
        }
    }

    @Test
    fun whenClickToFavorite_ShouldCallToggleFavoriteUseCase() = restaurantDetailsState {
        //When
        clickToFavoriteRestaurant()

        //Then
        coVerify { toggleRestaurantFavoriteStatus(any()) }
    }

    @Test
    fun whenOpened_ShouldMatchShowedDataWithRestaurantData() = restaurantDetailsState {
        //Then
        matchRestaurantStatus(mockedRestaurant.status)
        matchRestaurantIsFavorite(mockedRestaurant.isFavorite)
        matchRestaurantName(mockedRestaurant.name)
        matchRestaurantBestMatchValue(mockedRestaurant.bestMatch.toString())
        matchRestaurantNewestValue(mockedRestaurant.newest.toString())
        matchRestaurantRatingAverageValue(mockedRestaurant.ratingAverage.toString())
        matchRestaurantDistanceValue(mockedRestaurant.distance.toString())
        matchRestaurantPopularityValue(mockedRestaurant.popularity.toString())
        matchRestaurantAverageProductPriceValue(mockedRestaurant.averageProductPrice.toString())
        matchRestaurantDeliveryCostValue(mockedRestaurant.deliveryCosts.toString())
        matchRestaurantMinimumCostValue(mockedRestaurant.minCost.toString())
    }
}