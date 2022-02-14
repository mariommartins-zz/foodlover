package com.challenge.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.challenge.data.local.datasource.IRestaurantLocalDataSource
import com.challenge.data.remote.datasource.IRestaurantRemoteDataSource
import com.challenge.domain.repository.IRestaurantRepository
import com.challenge.testcore.factory.RestaurantFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class RestaurantRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val localDataSource = mockk<IRestaurantLocalDataSource>(relaxed = true)
    private val remoteDataSource = mockk<IRestaurantRemoteDataSource>(relaxed = true)
    private val favoriteStatusObserver = mockk<Observer<Boolean>>(relaxed = true)

    private val mockedRestaurant = RestaurantFactory.make()
    private val mockedRestaurantList = RestaurantFactory.makeList()

    private lateinit var repository: IRestaurantRepository

    init {
        coEvery { remoteDataSource.fetchRestaurants() } returns mockedRestaurantList
        coEvery { localDataSource.isFavorite(any()) } returns true
    }

    @Before
    fun setup() {
        //Given
        repository = RestaurantRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `when getAll is called Should call fetchRestaurants from remoteDataSource & update isFavorite correctly`() =
        runBlocking {
            //When
            val result = repository.getAll()

            //Then
            assertEquals(mockedRestaurantList.size, result.size)
            assertTrue { result.all { it.isFavorite } }
        }

    @Test
    fun `when addFavorite is called Should call add from localDataSource`() = runBlocking {
        //When
        repository.addFavorite(mockedRestaurant)

        //Then
        coVerify { localDataSource.add(mockedRestaurant) }
    }

    @Test
    fun `when removeFavorite is called Should call remove from localDataSource`() = runBlocking {
        //When
        repository.removeFavorite(mockedRestaurant)

        //Then
        coVerify { localDataSource.remove(mockedRestaurant) }
    }

    @Test
    fun `when isFavorite is called Should call isFavorite from localDataSource and return correct value`() =
        runBlocking {
            //When
            val result = repository.isFavorite(mockedRestaurant)

            //Then
            coVerify { localDataSource.isFavorite(mockedRestaurant.name) }
            assertTrue(result)
        }

    @Test
    fun `when observeFavoriteStatusBy is called Should call observeBy from localDataSource and Return correct observable`() {
        //Given
        every {
            localDataSource.observeFavoriteStatusBy(mockedRestaurant.name)
        } returns MutableLiveData(true)

        //When
        repository
            .observeFavoriteStatusBy(mockedRestaurant.name)
            .observeForever(favoriteStatusObserver)

        //Then
        verify { localDataSource.observeFavoriteStatusBy(mockedRestaurant.name) }
        verify { favoriteStatusObserver.onChanged(true) }
    }
}