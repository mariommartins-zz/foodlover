package com.challenge.foodlover.feature.restaurantlist

import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.testcore.util.FilterValidationHelper

internal class GetSortedRestaurantListTestHelper : GetSortedRestaurantListUseCase {

    override suspend operator fun invoke(filterOption: RestaurantFilterOption): List<Restaurant> =
        when (filterOption) {
            RestaurantFilterOption.BEST_MATCH -> FilterValidationHelper.bestMatchList
            RestaurantFilterOption.NEWEST -> FilterValidationHelper.newestList
            RestaurantFilterOption.RATING_AVERAGE -> FilterValidationHelper.ratingAverageList
            RestaurantFilterOption.DISTANCE -> FilterValidationHelper.distanceList
            RestaurantFilterOption.POPULARITY -> FilterValidationHelper.popularityList
            RestaurantFilterOption.AVERAGE_PRODUCT_PRICE ->
                FilterValidationHelper.averageProductPriceList
            RestaurantFilterOption.DELIVERY_COSTS -> FilterValidationHelper.deliveryCostList
            RestaurantFilterOption.MIN_COST -> FilterValidationHelper.minCostList
        }
}
