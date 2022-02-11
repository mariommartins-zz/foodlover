package com.challenge.kotlin

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherMap {

    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val computation: CoroutineDispatcher
}
