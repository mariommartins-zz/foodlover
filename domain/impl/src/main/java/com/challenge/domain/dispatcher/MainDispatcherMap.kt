package com.challenge.domain.dispatcher

import com.challenge.kotlin.dispatchers.DispatcherMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class MainDispatcherMap : DispatcherMap {

    override val io: CoroutineDispatcher = Dispatchers.IO
    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val computation: CoroutineDispatcher = Dispatchers.Default
}
