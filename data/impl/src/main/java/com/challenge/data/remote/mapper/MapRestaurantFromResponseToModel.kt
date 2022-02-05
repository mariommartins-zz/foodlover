package com.challenge.data.remote.mapper

import com.challenge.data.remote.response.RestaurantResponse
import com.challenge.data.util.Mapper
import com.challenge.domain.model.Restaurant

internal typealias MapRestaurantFromResponseToModelAlias = Mapper<RestaurantResponse, Restaurant>

internal class MapRestaurantFromResponseToModel : MapRestaurantFromResponseToModelAlias {
    override operator fun invoke(input: RestaurantResponse): Restaurant = with(input) {
        Restaurant(
            name = name,
            status = status,
            bestMatch = sortingValues.bestMatch,
            newest = sortingValues.newest,
            ratingAverage = sortingValues.ratingAverage,
            distance = sortingValues.distance,
            popularity = sortingValues.popularity,
            averageProductPrice = sortingValues.averageProductPrice,
            deliveryCosts = sortingValues.deliveryCosts,
            minCost = sortingValues.minCost
        )
    }
}
