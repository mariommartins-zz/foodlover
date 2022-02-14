package com.challenge.domain.usecase

import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.repository.IRestaurantRepository
import com.challenge.testcore.util.FilterValidationHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class GetSortedRestaurantListTest {

    private val repository = mockk<IRestaurantRepository>(relaxed = true)

    private lateinit var useCase: GetSortedRestaurantListUseCase

    init {
        coEvery { repository.getAll() } returns FilterValidationHelper.unsortedList
    }

    @Before
    fun setup() {
        //Given
        useCase = GetSortedRestaurantList(repository = repository)
    }

    @Test
    fun `when useCase is called Should call getAll from repository `() = runBlocking {
        //When
        useCase(RestaurantFilterOption.BEST_MATCH)

        //Then
        coVerify { repository.getAll() }
    }

    @Test
    fun `when useCase is called with BEST_MATCH filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.BEST_MATCH)

            //Then
            assertEquals(FilterValidationHelper.bestMatchList, result)
        }

    @Test
    fun `when useCase is called with NEWEST filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.NEWEST)

            //Then
            assertEquals(FilterValidationHelper.newestList, result)
        }

    @Test
    fun `when useCase is called with RATING_AVERAGE filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.RATING_AVERAGE)

            //Then
            assertEquals(FilterValidationHelper.ratingAverageList, result)
        }

    @Test
    fun `when useCase is called with DISTANCE filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.DISTANCE)

            //Then
            assertEquals(FilterValidationHelper.distanceList, result)
        }

    @Test
    fun `when useCase is called with POPULARITY filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.POPULARITY)

            //Then
            assertEquals(FilterValidationHelper.popularityList, result)
        }

    @Test
    fun `when useCase is called with AVERAGE_PRODUCT_PRICE filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.AVERAGE_PRODUCT_PRICE)

            //Then
            assertEquals(FilterValidationHelper.averageProductPriceList, result)
        }

    @Test
    fun `when useCase is called with DELIVERY_COSTS filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.DELIVERY_COSTS)

            //Then
            assertEquals(FilterValidationHelper.deliveryCostList, result)
        }

    @Test
    fun `when useCase is called with MIN_COST filterOption Should return correct ordered list`() =
        runBlocking {
            //When
            val result = useCase(RestaurantFilterOption.MIN_COST)

            //Then
            assertEquals(FilterValidationHelper.minCostList, result)
        }
}