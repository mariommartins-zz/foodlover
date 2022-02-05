package com.challenge.data.util

interface Mapper<in I, out O> {
    operator fun invoke(input: I): O
}
