package com.challenge.foodlover.util.actions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction

internal fun clickOnChildViewWithId(id: Int) = object : ViewAction {
    override fun getConstraints() = null

    override fun getDescription() = "Click on a child view with specified id."

    override fun perform(uiController: UiController, view: View) {
        view.findViewById<View>(id).performClick()
    }
}
