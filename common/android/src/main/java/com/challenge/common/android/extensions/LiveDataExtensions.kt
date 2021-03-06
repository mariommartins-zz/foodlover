package com.challenge.common.android.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T, R> LiveData<T>.map(mapFunction: (T) -> R): LiveData<R> =
    Transformations.map(this, mapFunction)