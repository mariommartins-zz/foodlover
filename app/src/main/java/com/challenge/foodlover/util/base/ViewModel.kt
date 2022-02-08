package com.challenge.foodlover.util.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class ViewModel<State : UIState, Action : UIAction>(
    initialState: State
) : ViewModel() {

//    private val viewModelState = State(initialState)
//    private val viewModelAction = Action<Action>()
//
//    val state: LiveData<State> = viewModelState.state
//
//    val action: LiveData<Action> = viewModelAction.action
//
//    protected fun setState(state: (State) -> State) {
//        viewModelState.setState(state)
//    }
//
//    protected open fun sendAction(action: () -> Action) {
//        viewModelAction.sendAction(action)
//    }
}
