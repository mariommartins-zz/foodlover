package com.challenge.common.android.presentationarch

import androidx.lifecycle.ViewModel

// The viewState is responsible by holding all the data that the view needs to render the layout
interface ViewState

// The viewAction is responsible by holding all the actions that could be performed by the view
interface ViewAction

/*
The viewModel owns a viewState and implements a viewAction.
This way it could update the state of the view when a specific action is called by the view.
*/
abstract class ViewModel<out State : ViewState, out Action : ViewAction> : ViewModel() {
    abstract val state: State
    abstract val action: Action
}