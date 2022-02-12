package com.challenge.testcore

import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.testcore.util.RandomGenerator

object RestaurantFactory {

    fun make(
        name: String = RandomGenerator.string(),
        status: RestaurantOpenStatus = RestaurantOpenStatus.CLOSED,
        bestMatch: Int = RandomGenerator.int(),
        newest: Int = RandomGenerator.int(),
        ratingAverage: Double = RandomGenerator.double(),
        distance: Int = RandomGenerator.int(),
        popularity: Int = RandomGenerator.int(),
        averageProductPrice: Int = RandomGenerator.int(),
        deliveryCosts: Int = RandomGenerator.int(),
        minCost: Int = RandomGenerator.int(),
        isFavorite: Boolean = RandomGenerator.boolean()
    ) = Restaurant(
        name = name,
        status = status,
        bestMatch = bestMatch,
        newest = newest,
        ratingAverage = ratingAverage,
        distance = distance,
        popularity = popularity,
        averageProductPrice = averageProductPrice,
        deliveryCosts = deliveryCosts,
        minCost = minCost,
        isFavorite = isFavorite
    )

    fun makeList(size: Int = 1) = List(size) { make() }
}