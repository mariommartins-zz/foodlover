package com.challenge.common.android.presentationarch.connectivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.IOFoodLoverException
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.JsonParsingFoodLoverException
import com.challenge.testcore.dispatcher.UnitTestDispatcherMap
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ErrorHandlingViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockedState: ViewState = mockk()
    private val mockedAction: ViewAction = mockk()
    private val testDispatcherMap = UnitTestDispatcherMap()
    private val onError = mockk<(ErrorCause) -> Unit>(relaxed = true)
    private val request = mockk<suspend () -> Unit>(relaxed = true)

    private val errorEventObserver = mockk<Observer<ErrorCause>>(relaxed = true)

    private lateinit var viewModel: ErrorHandlingViewModelExposer<ViewState, ViewAction>

    @Before
    fun setup() {
        //Given
        viewModel = object : ErrorHandlingViewModelExposer<ViewState, ViewAction>() {
            override val state: ViewState = mockedState
            override val action: ViewAction = mockedAction
            override val dispatcherMap = testDispatcherMap
        }
        viewModel.errorEvent.observeForever(errorEventObserver)
    }

    @Test
    fun `when performRequestSafely is called With success Should call request callback`() {
        //When
        viewModel.exposedPerformRequestSafely(onError, request)

        //Then
        coVerify { request() }
        verify(inverse = true) { onError(any()) }
    }

    @Test
    fun `when performRequestSafely is called With JsonParsingFoodLoverException Should call onError callback & post cause on _errorEvent`() {
        //Given
        coEvery { request() } throws JsonParsingFoodLoverException()

        //When
        viewModel.exposedPerformRequestSafely(onError, request)

        //Then
        verify { onError(ErrorCause.PARSING) }
        verify { errorEventObserver.onChanged(ErrorCause.PARSING) }
    }

    @Test
    fun `when performRequestSafely is called With IOFoodLoverException Should call onError callback & post cause on _errorEvent`() {
        //Given
        coEvery { request() } throws IOFoodLoverException()

        //When
        viewModel.exposedPerformRequestSafely(onError, request)

        //Then
        verify { onError(ErrorCause.ERROR) }
        verify { errorEventObserver.onChanged(ErrorCause.ERROR) }
    }
}
