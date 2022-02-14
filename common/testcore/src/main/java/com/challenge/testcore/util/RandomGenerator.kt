package com.challenge.testcore.util

import kotlin.random.Random

object RandomGenerator {

    fun string(size: Int = 5): String = List(size) {
        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
    }.toString()

    fun double(from: Double = 0.0, until: Double = 100.0) = Random.nextDouble(from, until)

    fun int(from: Int = 0, until: Int = 100) = Random.nextInt(from, until)

    fun boolean() = Random.nextBoolean()
}