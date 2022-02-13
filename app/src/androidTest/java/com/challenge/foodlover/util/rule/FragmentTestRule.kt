package com.challenge.foodlover.util.rule

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.challenge.foodlover.R
import com.challenge.foodlover.util.dispatchers.InstrumentedTestDispatcherMap
import com.challenge.kotlin.DispatcherMap
import io.mockk.mockk
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal class FragmentTestRule<T : Fragment>(
    val fragmentClass: Class<T>,
    @StyleRes val themeResId: Int = R.style.Theme_FoodLover,
    private val lifecycleInitState: Lifecycle.State = Lifecycle.State.RESUMED,
) : TestWatcher() {

    private var setupBeforeAction: () -> Unit = {}
    private var setupBeforeFragmentOpen: (T) -> Unit = {}
    private var setupBundleFragment: Bundle = Bundle()

    private var fragmentScenario: FragmentScenario<T>? = null

    val navController: NavController = mockk(relaxed = true)

    var fragment: Fragment? = null
        private set

    override fun starting(description: Description?) {
        super.starting(description)
        launchFragment()
    }

    override fun finished(description: Description?) {
        super.finished(description)
    }

    fun beforeAction(block: () -> Unit) = apply {
        setupBeforeAction = block
    }

    fun beforeFragmentOpen(block: (T) -> Unit) {
        setupBeforeFragmentOpen = block
    }

    fun putBundles(block: Bundle.() -> Unit) = apply {
        block.invoke(setupBundleFragment)
    }

    fun afterFragmentOpen(action: (T) -> Unit) {
        fragmentScenario?.onFragment(action)
    }

    private fun launchFragment() {
        setupDispatchers()
        setupBeforeAction()

        fragmentScenario = FragmentScenario.launchInContainer(
            fragmentClass = fragmentClass,
            fragmentArgs = setupBundleFragment,
            themeResId = themeResId,
            initialState = lifecycleInitState,
            factory = null
        ).also(::setupFragmentInitialization)
    }

    private fun setupDispatchers() {
        module(override = true) {
            single<DispatcherMap> { InstrumentedTestDispatcherMap }
        }.also { loadKoinModules(it) }
    }

    private fun setupFragmentInitialization(fragmentScenario: FragmentScenario<T>) {
        fragmentScenario.onFragment { frag ->
            setupBeforeFragmentOpen(frag)
            configureNavigationController(frag)
            fragment = frag
        }

        if (lifecycleInitState != Lifecycle.State.RESUMED) {
            fragmentScenario.moveToState(Lifecycle.State.RESUMED)
        }
    }

    private fun configureNavigationController(fragment: T) {
        fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
            if (viewLifecycleOwner != null) {
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}
