package com.challenge.data.util.factory

import com.challenge.data.remote.response.RestaurantResponse
import com.challenge.data.remote.response.RestaurantSortingValuesResponse
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.testcore.util.RandomGenerator

internal object RestaurantResponseFactory {

    fun make(
        name: String = RandomGenerator.string(),
        status: String = RestaurantOpenStatus.CLOSED.name,
        sortingValues: RestaurantSortingValuesResponse = makeRestaurantSortingValuesResponse()
    ) = RestaurantResponse(
        name = name,
        status = status,
        sortingValues = sortingValues
    )

    fun makeList(size: Int = 1) = List(size) { make() }

    private fun makeRestaurantSortingValuesResponse(
        bestMatch: Int = RandomGenerator.int(),
        newest: Int = RandomGenerator.int(),
        ratingAverage: Double = RandomGenerator.double(),
        distance: Int = RandomGenerator.int(),
        popularity: Int = RandomGenerator.int(),
        averageProductPrice: Int = RandomGenerator.int(),
        deliveryCosts: Int = RandomGenerator.int(),
        minCost: Int = RandomGenerator.int()
    ) = RestaurantSortingValuesResponse(
        bestMatch = bestMatch,
        newest = newest,
        ratingAverage = ratingAverage,
        distance = distance,
        popularity = popularity,
        averageProductPrice = averageProductPrice,
        deliveryCosts = deliveryCosts,
        minCost = minCost
    )
}