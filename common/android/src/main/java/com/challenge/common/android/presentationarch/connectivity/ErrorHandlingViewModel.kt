package com.challenge.common.android.presentationarch.connectivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewModel
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.ERROR
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.PARSING
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.IOFoodLoverException
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.JsonParsingFoodLoverException
import com.challenge.common.android.util.SingleLiveEvent
import com.challenge.kotlin.dispatchers.DispatcherMap
import com.challenge.kotlin.extensions.exhaustive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ErrorHandlingViewModel<out State : ViewState, out Action : ViewAction> :
    ViewModel<State, Action>() {

    abstract val dispatcherMap: DispatcherMap

    private val _errorEvent = SingleLiveEvent<ErrorCause>()
    val errorEvent: LiveData<ErrorCause> get() = _errorEvent

    protected fun performRequestSafely(
        onError: (ErrorCause) -> Unit = {},
        request: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcherMap.ui) {
            try {
                withContext(dispatcherMap.io) { request.invoke() }
            } catch (exception: FoodLoverException) {
                handleFoodLoverException(onError, exception)
            }
        }
    }

    private fun handleFoodLoverException(
        onError: (ErrorCause) -> Unit = {},
        exception: FoodLoverException
    ) = when (exception) {
        is JsonParsingFoodLoverException -> onLoadException(PARSING, onError)
        is IOFoodLoverException -> onLoadException(ERROR, onError)
    }.exhaustive

    private fun onLoadException(cause: ErrorCause, onError: (ErrorCause) -> Unit = {}) {
        _errorEvent.postValue(cause)
        onError(cause)
    }
}
