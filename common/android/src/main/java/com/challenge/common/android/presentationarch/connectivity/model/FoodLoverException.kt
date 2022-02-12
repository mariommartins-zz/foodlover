package com.challenge.common.android.presentationarch.connectivity.model

sealed class FoodLoverException : Exception() {
    class JsonParsingFoodLoverException : FoodLoverException()
    class IOFoodLoverException : FoodLoverException()
}
