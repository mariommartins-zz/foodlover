package com.challenge.foodlover.util.presentationarch

import androidx.lifecycle.ViewModel

interface ViewState
interface ViewAction

abstract class ViewModel<out State : ViewState, out Action : ViewAction> : ViewModel(), ViewAction {
    abstract val state: State
    val action: ViewAction get() = this
}