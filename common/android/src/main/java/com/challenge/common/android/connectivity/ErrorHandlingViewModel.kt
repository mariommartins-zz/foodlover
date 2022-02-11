package com.challenge.common.android.connectivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.challenge.common.android.connectivity.Cause.ERROR
import com.challenge.common.android.connectivity.Cause.NO_INTERNET
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewModel
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.util.SingleLiveEvent
import com.challenge.kotlin.DispatcherMap
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

abstract class ErrorHandlingViewModel<out State : ViewState, out Action : ViewAction> :
    ViewModel<State, Action>() {

    abstract val dispatcherMap: DispatcherMap

    private val _networkErrorEvent = SingleLiveEvent<Any>()
    val networkErrorEvent: LiveData<Any> get() = _networkErrorEvent

    private val _genericErrorEvent = SingleLiveEvent<Any>()
    val genericErrorEvent: LiveData<Any> get() = _genericErrorEvent

    protected fun performRequestSafely(
        onError: (Cause) -> Unit = {},
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcherMap.ui) {
            try {
                withContext(dispatcherMap.io) { block.invoke() }
            } catch (exception: JsonSyntaxException) {
                onLoadException(NO_INTERNET, onError)
            } catch (exception: IOException) {
                onLoadException(ERROR, onError)
            }
        }
    }

    private fun onLoadException(cause: Cause, onError: (Cause) -> Unit = {}) {
        when (cause) {
            NO_INTERNET -> _networkErrorEvent.call()
            ERROR -> _genericErrorEvent.call()
        }
        onError(cause)
    }
}

enum class Cause {
    NO_INTERNET,
    ERROR
}
