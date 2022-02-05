package com.challenge.data.local.datasource

import com.challenge.common.android.map
import com.challenge.data.local.dao.RestaurantDao
import com.challenge.data.local.mapper.MapRestaurantFromModelToEntityAlias
import com.challenge.domain.model.Restaurant

internal class RestaurantLocalDataSource(
    private val dao: RestaurantDao,
    private val mapFromModelToEntity: MapRestaurantFromModelToEntityAlias,
) : IRestaurantLocalDataSource {
    override suspend fun add(restaurant: Restaurant) = dao.insert(mapFromModelToEntity(restaurant))

    override suspend fun remove(restaurant: Restaurant) = dao.deleteBy(restaurant.name)

    override suspend fun isFavorite(name: String) = dao.getBy(name) != null

    override fun observeFavoriteStatusBy(name: String) = dao.observeBy(name).map { it != null }
}
