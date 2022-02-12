package com.challenge.common.android.presentationarch.connectivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewModel
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.ERROR
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.PARSING
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.IOFoodLoverException
import com.challenge.common.android.presentationarch.connectivity.model.FoodLoverException.JsonParsingFoodLoverException
import com.challenge.common.android.util.SingleLiveEvent
import com.challenge.kotlin.DispatcherMap
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
            } catch (exception: JsonParsingFoodLoverException) {
                onLoadException(PARSING, onError)
            } catch (exception: IOFoodLoverException) {
                onLoadException(ERROR, onError)
            }
        }
    }

    private fun onLoadException(cause: ErrorCause, onError: (ErrorCause) -> Unit = {}) {
        _errorEvent.postValue(cause)
        onError(cause)
    }
}
