package com.challenge.foodlover.feature.restaurantlist

import androidx.navigation.NavDirections
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.foodlover.R
import com.challenge.foodlover.util.rule.FragmentTestRule
import com.challenge.testcore.util.FilterValidationHelper
import io.mockk.spyk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal class RestaurantListFragmentTest {

    private val getSortedRestaurantList = spyk(GetSortedRestaurantListTestHelper())

    @get:Rule
    val fragmentTestRule = FragmentTestRule(RestaurantListFragment::class.java)

    init {
        fragmentTestRule.beforeAction {
            module(override = true) {
                factory<GetSortedRestaurantListUseCase> { getSortedRestaurantList }
            }.also { loadKoinModules(it) }
        }
    }

    @Test
    fun whenFirstlyGetRestaurantList_ShouldDisplayBestMatchSortedList() = restaurantListState {
        //Then
        matchFilterOptionSelected(R.string.restaurant_sorting_option_best_match)
        matchAmountOfListedItems(amount = FilterValidationHelper.bestMatchList.size)
        matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.bestMatchList)
    }

    @Test
    fun whenSelectRestaurantFromList_ShouldCallNavigation() = restaurantListState {
        //When
        clickOnRestaurantItem(0)

        //Then
        verify { fragmentTestRule.navController.navigate(any<NavDirections>()) }
    }

    @Test
    fun whenSelectNewestFilterOption_ShouldDisplayNewestSortedList() = restaurantListState {
        //When
        clickOnFilterOptionBy(RestaurantFilterOption.NEWEST.value)

        //Then
        matchFilterOptionSelected(R.string.restaurant_sorting_option_newest)
        matchAmountOfListedItems(amount = FilterValidationHelper.newestList.size)
        matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.newestList)
    }

    @Test
    fun whenSelectRatingAverageFilterOption_ShouldDisplayRatingAverageSortedList() =
        restaurantListState {
            //When
            clickOnFilterOptionBy(RestaurantFilterOption.RATING_AVERAGE.value)

            //Then
            matchFilterOptionSelected(R.string.restaurant_sorting_option_rating_average)
            matchAmountOfListedItems(amount = FilterValidationHelper.ratingAverageList.size)
            matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.ratingAverageList)
        }

    @Test
    fun whenSelectDistanceFilterOption_ShouldDisplayDistanceSortedList() = restaurantListState {
        //When
        clickOnFilterOptionBy(RestaurantFilterOption.DISTANCE.value)

        //Then
        matchFilterOptionSelected(R.string.restaurant_sorting_option_distance)
        matchAmountOfListedItems(amount = FilterValidationHelper.distanceList.size)
        matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.distanceList)
    }

    @Test
    fun whenSelectPopularityFilterOption_ShouldDisplayPopularitySortedList() = restaurantListState {
        //When
        clickOnFilterOptionBy(RestaurantFilterOption.POPULARITY.value)

        //Then
        matchFilterOptionSelected(R.string.restaurant_sorting_option_popularity)
        matchAmountOfListedItems(amount = FilterValidationHelper.popularityList.size)
        matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.popularityList)
    }

    @Test
    fun whenSelectAverageProductPriceFilterOption_ShouldDisplayAverageProductPriceSortedList() =
        restaurantListState {
            //When
            clickOnFilterOptionBy(RestaurantFilterOption.AVERAGE_PRODUCT_PRICE.value)

            //Then
            matchFilterOptionSelected(R.string.restaurant_sorting_option_average_product_price)
            matchAmountOfListedItems(amount = FilterValidationHelper.averageProductPriceList.size)
            matchListedRestaurantCardsValuesBy(
                expected = FilterValidationHelper.averageProductPriceList
            )
        }

    @Test
    fun whenSelectDeliveryCostsFilterOption_ShouldDisplayDeliveryCostsSortedList() =
        restaurantListState {
            //When
            clickOnFilterOptionBy(RestaurantFilterOption.DELIVERY_COSTS.value)

            //Then
            matchFilterOptionSelected(R.string.restaurant_sorting_option_delivery_cost)
            matchAmountOfListedItems(amount = FilterValidationHelper.deliveryCostList.size)
            matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.deliveryCostList)
        }

    @Test
    fun whenSelectMinCostFilterOption_ShouldDisplayMinCostSortedList() = restaurantListState {
        //When
        clickOnFilterOptionBy(RestaurantFilterOption.MIN_COST.value)

        //Then
        matchFilterOptionSelected(R.string.restaurant_sorting_option_minimum_cost)
        matchAmountOfListedItems(amount = FilterValidationHelper.minCostList.size)
        matchListedRestaurantCardsValuesBy(expected = FilterValidationHelper.minCostList)
    }
}