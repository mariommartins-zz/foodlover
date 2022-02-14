package com.challenge.common.android.presentationarch.connectivity

import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause

abstract class ErrorHandlingViewModelExposer<out State : ViewState, out Action : ViewAction> :
    ErrorHandlingViewModel<State, Action>() {

    fun exposedPerformRequestSafely(
        onError: (ErrorCause) -> Unit = {},
        request: suspend () -> Unit
    ) = performRequestSafely(onError, request)
}
