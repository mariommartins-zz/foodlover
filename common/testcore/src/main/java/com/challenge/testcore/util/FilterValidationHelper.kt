package com.challenge.testcore.util

import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.testcore.factory.RestaurantFactory

object FilterValidationHelper {

    private val rest1 = RestaurantFactory.make(
        name = "rest1",
        status = RestaurantOpenStatus.CLOSED,
        bestMatch = 2,
        newest = 2,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = true
    )
    private val rest2 = RestaurantFactory.make(
        name = "rest2",
        status = RestaurantOpenStatus.CLOSED,
        bestMatch = 1,
        newest = 1,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = true
    )
    private val rest3 = RestaurantFactory.make(
        name = "rest3",
        status = RestaurantOpenStatus.OPEN,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 2.0,
        distance = 2,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = false
    )
    private val rest4 = RestaurantFactory.make(
        name = "rest4",
        status = RestaurantOpenStatus.OPEN,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 1.0,
        distance = 1,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = false
    )
    private val rest5 = RestaurantFactory.make(
        name = "rest5",
        status = RestaurantOpenStatus.OPEN,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 2,
        averageProductPrice = 2,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = true
    )
    private val rest6 = RestaurantFactory.make(
        name = "rest6",
        status = RestaurantOpenStatus.OPEN,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 1,
        averageProductPrice = 1,
        deliveryCosts = 0,
        minCost = 0,
        isFavorite = true
    )
    private val rest7 = RestaurantFactory.make(
        name = "rest7",
        status = RestaurantOpenStatus.ORDER_AHEAD,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 1,
        minCost = 2,
        isFavorite = false
    )
    private val rest8 = RestaurantFactory.make(
        name = "rest8",
        status = RestaurantOpenStatus.ORDER_AHEAD,
        bestMatch = 0,
        newest = 0,
        ratingAverage = 0.0,
        distance = 0,
        popularity = 0,
        averageProductPrice = 0,
        deliveryCosts = 2,
        minCost = 1,
        isFavorite = false
    )

    val unsortedList =
        listOf(rest1, rest2, rest3, rest4, rest5, rest6, rest7, rest8)

    val bestMatchList =
        listOf(rest5, rest6, rest1, rest2, rest3, rest4, rest7, rest8)
    val newestList =
        listOf(rest5, rest6, rest2, rest1, rest3, rest4, rest7, rest8)

    val ratingAverageList =
        listOf(rest5, rest6, rest1, rest2, rest3, rest4, rest7, rest8)
    val distanceList =
        listOf(rest5, rest6, rest1, rest2, rest4, rest3, rest7, rest8)

    val popularityList =
        listOf(rest5, rest6, rest1, rest2, rest3, rest4, rest7, rest8)
    val averageProductPriceList =
        listOf(rest6, rest5, rest1, rest2, rest3, rest4, rest7, rest8)

    val deliveryCostList =
        listOf(rest5, rest6, rest1, rest2, rest3, rest4, rest7, rest8)
    val minCostList =
        listOf(rest5, rest6, rest1, rest2, rest3, rest4, rest8, rest7)
}