package com.challenge.common.android.extensions

fun Int.asMoney(): String = (toFloat() / 100).toString()