package com.challenge.common.android.presentationarch.connectivity

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.challenge.common.android.R
import com.challenge.common.android.extensions.showWarningSnackbar
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.ERROR
import com.challenge.common.android.presentationarch.connectivity.model.ErrorCause.NO_INTERNET

abstract class ErrorHandlingFragment : Fragment() {

    protected abstract val binding: ViewDataBinding
    protected abstract val viewModel: ErrorHandlingViewModel<ViewState, ViewAction>

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doOnViewCreated(view, savedInstanceState)
        observeEvents()
    }

    protected abstract fun doOnViewCreated(view: View, savedInstanceState: Bundle?)

    private fun observeEvents() = with(viewModel) {
        errorEvent.observe(viewLifecycleOwner) {
            when (it) {
                NO_INTERNET -> showWarningSnackbar(binding.root, R.string.network_error_text)
                ERROR -> showWarningSnackbar(binding.root, R.string.generic_error_text)
                null -> showWarningSnackbar(binding.root, R.string.generic_error_text)
            }
        }
    }
}
