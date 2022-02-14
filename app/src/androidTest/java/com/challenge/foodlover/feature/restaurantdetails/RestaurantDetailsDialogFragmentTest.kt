package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.common.android.extensions.asMoney
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.foodlover.util.rule.FragmentTestRule
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
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

    @Before
    fun setup() {
        RestaurantDetailsRobot.resources = fragmentTestRule.fragment?.resources
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
        matchRestaurantDistanceValue(mockedRestaurant.distance)
        matchRestaurantPopularityValue(mockedRestaurant.popularity.toString())
        matchRestaurantAverageProductPriceValue(mockedRestaurant.averageProductPrice.asMoney())
        matchRestaurantDeliveryCostValue(mockedRestaurant.deliveryCosts.asMoney())
        matchRestaurantMinimumCostValue(mockedRestaurant.minCost.asMoney())
    }
}
