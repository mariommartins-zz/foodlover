package com.challenge.foodlover.util

import com.challenge.kotlin.DispatcherMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class UnitTestDispatcherMap : DispatcherMap {

    override val io: CoroutineDispatcher = Dispatchers.Unconfined
    override val ui: CoroutineDispatcher = Dispatchers.Unconfined
    override val computation: CoroutineDispatcher = Dispatchers.Unconfined
}
