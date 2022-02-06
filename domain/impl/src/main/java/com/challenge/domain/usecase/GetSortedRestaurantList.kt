package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.repository.IRestaurantRepository

internal class GetSortedRestaurantList(private val repository: IRestaurantRepository) :
    GetSortedRestaurantListUseCase {

    private val defaultComparator =
        compareByDescending<Restaurant> { it.isFavorite }.thenBy { it.status.priority }

    override suspend operator fun invoke(filterOption: RestaurantFilterOption): List<Restaurant> {
        val restaurants = repository.getAll()

        val comparator = when (filterOption) {
            RestaurantFilterOption.BEST_MATCH ->
                defaultComparator.thenByDescending { it.bestMatch }
            RestaurantFilterOption.NEWEST ->
                defaultComparator.thenBy { it.newest }
            RestaurantFilterOption.RATING_AVERAGE ->
                defaultComparator.thenByDescending { it.ratingAverage }
            RestaurantFilterOption.DISTANCE ->
                defaultComparator.thenBy { it.distance }
            RestaurantFilterOption.POPULARITY ->
                defaultComparator.thenByDescending { it.popularity }
            RestaurantFilterOption.AVERAGE_PRODUCT_PRICE ->
                defaultComparator.thenBy { it.averageProductPrice }
            RestaurantFilterOption.DELIVERY_COSTS ->
                defaultComparator.thenBy { it.deliveryCosts }
            RestaurantFilterOption.MIN_COST ->
                defaultComparator.thenBy { it.minCost }
        }

        return restaurants.sortedWith(comparator)
    }
}
