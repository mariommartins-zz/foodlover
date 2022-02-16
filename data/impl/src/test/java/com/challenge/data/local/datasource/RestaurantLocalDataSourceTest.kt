package com.challenge.data.local.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.challenge.data.local.database.RestaurantDao
import com.challenge.data.local.entity.RestaurantEntity
import com.challenge.data.local.mapper.MapRestaurantFromModelToEntityAlias
import com.challenge.data.util.factory.RestaurantEntityFactory
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class RestaurantLocalDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dao = mockk<RestaurantDao>(relaxed = true)
    private val mapFromModelToEntity = mockk<MapRestaurantFromModelToEntityAlias>(relaxed = true)
    private val favoriteStatusObserver = mockk<Observer<Boolean>>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()
    private val mockedRestaurantEntity = RestaurantEntityFactory.make()

    private lateinit var dataSource: IRestaurantLocalDataSource

    init {
        every { mapFromModelToEntity(mockedRestaurant) } returns mockedRestaurantEntity
    }

    @Before
    fun setup() {
        //Given
        dataSource = RestaurantLocalDataSource(
            dao = dao,
            mapFromModelToEntity = mapFromModelToEntity
        )
    }

    @Test
    fun `when add is called Should call mapper & insert from dao`() = runBlocking {
        //When
        dataSource.add(mockedRestaurant)

        //Then
        coVerifyOrder {
            mapFromModelToEntity(mockedRestaurant)
            dao.insert(mockedRestaurantEntity)
        }
    }

    @Test
    fun `when remove is called Should call deleteBy from dao`() = runBlocking {
        //When
        dataSource.remove(mockedRestaurant)

        //Then
        coVerify { dao.deleteBy(mockedRestaurant.name) }
    }

    @Test
    fun `when isFavorite is called With favorited restaurant Should call getBy from dao and Return true`() =
        runBlocking {
            //Given
            coEvery { dao.getBy(mockedRestaurant.name) } returns mockedRestaurantEntity

            //When
            val result = dataSource.isFavorite(mockedRestaurant.name)

            //Then
            coVerify { dao.getBy(mockedRestaurant.name) }
            assertTrue(result)
        }

    @Test
    fun `when isFavorite is called With unfavorited restaurant Should call getBy from dao and Return false`() =
        runBlocking {
            //Given
            coEvery { dao.getBy(mockedRestaurant.name) } returns null

            //When
            val result = dataSource.isFavorite(mockedRestaurant.name)

            //Then
            coVerify { dao.getBy(mockedRestaurant.name) }
            assertFalse(result)
        }

    @Test
    fun `when observeFavoriteStatusBy is called With favorited restaurant Should call observeBy from dao and Return true observable`() {
        //Given
        coEvery {
            dao.observeBy(mockedRestaurant.name)
        } returns MutableLiveData<RestaurantEntity?>(mockedRestaurantEntity)

        //When
        dataSource
            .observeFavoriteStatusBy(mockedRestaurant.name)
            .observeForever(favoriteStatusObserver)

        //Then
        verify { dao.observeBy(mockedRestaurant.name) }
        verify { favoriteStatusObserver.onChanged(true) }
    }

    @Test
    fun `when observeFavoriteStatusBy is called With unfavorited restaurant Should call observeBy from dao and Return false observable`() {
        //Given
        coEvery {
            dao.observeBy(mockedRestaurant.name)
        } returns MutableLiveData<RestaurantEntity?>(null)

        //When
        dataSource
            .observeFavoriteStatusBy(mockedRestaurant.name)
            .observeForever(favoriteStatusObserver)

        //Then
        verify { dao.observeBy(mockedRestaurant.name) }
        verify { favoriteStatusObserver.onChanged(false) }
    }
}