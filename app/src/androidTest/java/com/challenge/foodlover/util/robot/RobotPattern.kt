package com.challenge.foodlover.util.robot

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.challenge.foodlover.util.actions.clickOnChildViewWithId
import com.challenge.foodlover.util.assertions.atPosition
import com.challenge.foodlover.util.assertions.withItemCount
import com.challenge.foodlover.util.assertions.withRatingBarValue
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything

internal fun clickOnView(@IdRes viewId: Int): ViewInteraction =
    onView(withId(viewId)).perform(click())

internal fun <T : RecyclerView.ViewHolder> clickOnElementOfDisplayedListItem(
    @IdRes listId: Int,
    @IdRes viewId: Int,
    position: Int
): ViewInteraction = onView(allOf(withId(listId), isDisplayed()))
    .perform(actionOnItemAtPosition<T>(position, clickOnChildViewWithId(viewId)))

internal fun clickOnElementOfSpinner(position: Int) {
    onData(anything()).atPosition(position).perform(click())
}

internal fun swipeItemOn(@IdRes recyclerView: Int, position: Int): ViewInteraction =
    onView(withId(recyclerView))
        .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.swipeLeft()))

internal fun swipeTo(@IdRes viewId: Int): ViewInteraction =
    onView(withId(viewId)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))

internal fun matchWithItemCount(@IdRes viewId: Int, amount: Int): ViewInteraction =
    onView(allOf(withId(viewId), isDisplayed())).check(matches(withItemCount(amount)))

internal fun matchText(@IdRes viewId: Int, @StringRes stringId: Int): ViewInteraction =
    onView(withId(viewId)).check(matches(withText(stringId)))

internal fun matchText(@IdRes viewId: Int, text: String): ViewInteraction =
    onView(withId(viewId)).check(matches(withText(text)))

internal fun matchTextAtIncludedView(
    @IdRes containerId: Int,
    @IdRes viewId: Int,
    text: String
): ViewInteraction =
    onView(withId(containerId)).check(matches(hasDescendant(allOf(withId(viewId), withText(text)))))

internal fun matchSpinnerText(@IdRes viewId: Int, @StringRes stringId: Int): ViewInteraction =
    onView(withId(viewId)).check(matches(withSpinnerText(stringId)))

internal fun matchTextOfDisplayedListItem(
    @IdRes listId: Int,
    position: Int,
    @IdRes viewId: Int,
    text: String
): ViewInteraction = onView(withId(listId)).check(
    matches(atPosition(position, hasDescendant(allOf(withId(viewId), withText(text)))))
)

internal fun matchTextOfDisplayedListItem(
    @IdRes listId: Int,
    position: Int,
    @IdRes viewId: Int,
    @StringRes stringId: Int
): ViewInteraction = onView(withId(listId)).check(
    matches(atPosition(position, hasDescendant(allOf(withId(viewId), withText(stringId)))))
)

internal fun matchIsCheckedOnDisplayedListItem(
    @IdRes listId: Int,
    position: Int,
    @IdRes viewId: Int
): ViewInteraction = onView(withId(listId)).check(
    matches(atPosition(position, hasDescendant(allOf(withId(viewId), ViewMatchers.isChecked()))))
)

internal fun matchIsNotCheckedOnDisplayedListItem(
    @IdRes listId: Int,
    position: Int,
    @IdRes viewId: Int
): ViewInteraction = onView(withId(listId)).check(
    matches(
        atPosition(
            position,
            hasDescendant(allOf(withId(viewId), Matchers.not(ViewMatchers.isChecked())))
        )
    )
)

internal fun matchRatingBarValueOnDisplayedListItem(
    @IdRes listId: Int,
    position: Int,
    @IdRes viewId: Int,
    value: Float
): ViewInteraction = onView(withId(listId)).check(
    matches(atPosition(position, hasDescendant(allOf(withId(viewId), withRatingBarValue(value)))))
)

internal fun matchIsChecked(@IdRes viewId: Int): ViewInteraction =
    onView(withId(viewId)).check(matches(ViewMatchers.isChecked()))

internal fun matchIsNotChecked(@IdRes viewId: Int): ViewInteraction =
    onView(withId(viewId)).check(matches(Matchers.not(ViewMatchers.isChecked())))
