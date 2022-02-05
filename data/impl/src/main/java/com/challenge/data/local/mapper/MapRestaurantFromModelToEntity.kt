package com.challenge.data.local.mapper

import com.challenge.data.local.entity.RestaurantEntity
import com.challenge.data.util.Mapper
import com.challenge.domain.model.Restaurant

internal typealias MapRestaurantFromModelToEntityAlias = Mapper<Restaurant, RestaurantEntity>

internal class MapRestaurantFromModelToEntity : MapRestaurantFromModelToEntityAlias {
    override operator fun invoke(input: Restaurant) = RestaurantEntity(name = input.name)
}
