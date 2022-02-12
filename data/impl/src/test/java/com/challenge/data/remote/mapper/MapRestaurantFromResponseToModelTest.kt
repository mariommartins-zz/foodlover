package com.challenge.data.remote.mapper

import com.challenge.data.util.factory.RestaurantResponseFactory
import com.challenge.domain.model.RestaurantOpenStatus
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class MapRestaurantFromResponseToModelTest {

    private lateinit var mapper: MapRestaurantFromResponseToModelAlias

    @Before
    fun setup() {
        //Given
        mapper = MapRestaurantFromResponseToModel()
    }

    @Test
    fun `when mapper is called With input restaurant response Should return valid domain instance`() {
        //Given
        val response = RestaurantResponseFactory.make()

        //When
        val result = mapper(response)

        //Then
        assertEquals(response.name, result!!.name)
        assertEquals(RestaurantOpenStatus.safeValueOf(response.status), result.status)
        assertEquals(response.sortingValues.bestMatch, result.bestMatch)
        assertEquals(response.sortingValues.newest, result.newest)
        assertEquals(response.sortingValues.ratingAverage, result.ratingAverage)
        assertEquals(response.sortingValues.distance, result.distance)
        assertEquals(response.sortingValues.popularity, result.popularity)
        assertEquals(response.sortingValues.averageProductPrice, result.averageProductPrice)
        assertEquals(response.sortingValues.deliveryCosts, result.deliveryCosts)
        assertEquals(response.sortingValues.minCost, result.minCost)
    }

    @Test
    fun `when mapper is called With input restaurant response with invalid status response Should return null`() {
        //Given
        val response = RestaurantResponseFactory.make(status = "")

        //When
        val result = mapper(response)

        //Then
        assertNull(result)
    }
}