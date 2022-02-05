package com.challenge.common

interface Mapper<in I, out O> {
    fun from(input: I): O
}
