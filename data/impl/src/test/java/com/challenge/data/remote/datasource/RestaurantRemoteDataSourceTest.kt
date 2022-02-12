package com.challenge.data.remote.datasource

import com.challenge.data.remote.mapper.MapRestaurantFromResponseToModelAlias
import com.challenge.data.remote.network.FoodLoverApi
import com.challenge.data.util.factory.RestaurantResponseFactory
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class RestaurantRemoteDataSourceTest {

    private val api = mockk<FoodLoverApi>(relaxed = true)
    private val mapFromResponseToModel =
        mockk<MapRestaurantFromResponseToModelAlias>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()

    private lateinit var dataSource: IRestaurantRemoteDataSource

    @Before
    fun setup() {
        //Given
        dataSource = RestaurantRemoteDataSource(
            api = api,
            mapFromResponseToModel = mapFromResponseToModel
        )
    }

    @Test
    fun `when fetchRestaurants is called With invalid items Should call mapper & return list without nulls`() =
        runBlocking {
            //Given
            val responseList = RestaurantResponseFactory.makeList()
            coEvery { api.fetchRestaurants() } returns responseList

            every { mapFromResponseToModel(any()) } returns null

            //When
            val result = dataSource.fetchRestaurants()

            //Then
            coVerify { api.fetchRestaurants() }
            verify { mapFromResponseToModel(any()) }
            assertTrue(result.isEmpty())
        }

    @Test
    fun `when fetchRestaurants is called With valid items Should call mapper & return list`() =
        runBlocking {
            //Given
            val responseList = RestaurantResponseFactory.makeList()
            coEvery { api.fetchRestaurants() } returns responseList

            every { mapFromResponseToModel(any()) } returns mockedRestaurant

            //When
            val result = dataSource.fetchRestaurants()

            //Then
            coVerify { api.fetchRestaurants() }
            verify { mapFromResponseToModel(any()) }
            assertEquals(responseList.size, result.size)
        }
}
