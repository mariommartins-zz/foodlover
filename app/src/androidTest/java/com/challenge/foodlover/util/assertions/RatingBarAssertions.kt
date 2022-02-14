package com.challenge.foodlover.util.assertions

import android.view.View
import android.widget.RatingBar
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

internal fun withRatingBarValue(value: Float): Matcher<View?> =
    object : BoundedMatcher<View?, RatingBar>(RatingBar::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has rating bar value: $value")
        }

        override fun matchesSafely(view: RatingBar): Boolean = view.rating == value
    }