package com.challenge.common.android.extensions

private const val CENTS_DIVISOR = 100

fun Int.asMoney(): String = (toFloat() / CENTS_DIVISOR).toString()