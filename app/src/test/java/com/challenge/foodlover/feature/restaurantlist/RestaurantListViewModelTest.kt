package com.challenge.foodlover.feature.restaurantlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.testcore.RestaurantFactory
import com.challenge.testcore.dispatcher.UnitTestDispatcherMap
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RestaurantListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcherMap = UnitTestDispatcherMap()
    private val getSortedRestaurantList =
        mockk<GetSortedRestaurantListUseCase>(relaxed = true)
    private val mutableState = mockk<RestaurantListViewState>(relaxed = true)

    private val mockedRestaurants = RestaurantFactory.makeList()

    private lateinit var viewModel: IRestaurantListViewAction

    init {
        coEvery { getSortedRestaurantList(any()) } returns mockedRestaurants
    }

    @Before
    fun setup() {
        //Given
        viewModel = RestaurantListViewModel(
            getSortedRestaurantList = getSortedRestaurantList,
            dispatcherMap = dispatcherMap,
            mutableState = mutableState
        )
    }

    @Test
    fun `when create Should call getSortedRestaurantList && post returned restaurants`() {
        //Then
        coVerify { getSortedRestaurantList(RestaurantFilterOption.BEST_MATCH) }
        verify { mutableState.postRestaurants(mockedRestaurants) }
    }

    @Test
    fun `when onSwipeToRefresh is called Should execute the same creation code again`() {
        //When
        viewModel.onSwipeToRefresh()

        //Then
        coVerify(exactly = 2) { getSortedRestaurantList(RestaurantFilterOption.BEST_MATCH) }
        verify(exactly = 2) { mutableState.postRestaurants(mockedRestaurants) }
    }

    @Test
    fun `when onFilterOptionSelected is called With filterValue param Should call getSortedRestaurantList`() {
        //Given
        val filterValue = 1

        //When
        viewModel.onFilterOptionSelected(filterValue)

        //Then
        coVerify { getSortedRestaurantList(RestaurantFilterOption.getByValue(filterValue)!!) }
    }

    @Test
    fun `when onFilterOptionSelected is called twice With the same filterValue param Should call getSortedRestaurantList only once`() {
        //Given
        val filterValue = 1

        //When
        viewModel.onFilterOptionSelected(filterValue)
        viewModel.onFilterOptionSelected(filterValue)

        //Then
        coVerify(exactly = 1) { getSortedRestaurantList(RestaurantFilterOption.getByValue(filterValue)!!) }
    }
}
