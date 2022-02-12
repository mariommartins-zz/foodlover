package com.challenge.data.local.mapper

import com.challenge.testcore.factory.RestaurantFactory
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class MapRestaurantFromModelToEntityTest {

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var mapper: MapRestaurantFromModelToEntityAlias

    @Before
    fun setup() {
        //Given
        mapper = MapRestaurantFromModelToEntity()
    }

    @Test
    fun `when mapper is called With input restaurant domain Should return entity instance`() {
        //When
        val result = mapper(mockedRestaurant)

        //Then
        assertEquals(mockedRestaurant.name, result.name)
    }
}