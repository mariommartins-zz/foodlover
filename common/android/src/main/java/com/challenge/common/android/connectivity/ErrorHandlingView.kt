package com.challenge.common.android.connectivity

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.challenge.common.android.R
import com.challenge.common.android.extensions.showWarningSnackbar
import com.challenge.common.android.presentationarch.ViewAction
import com.challenge.common.android.presentationarch.ViewState

interface ErrorHandlingView {

    val binding: ViewDataBinding
    val viewModel: ErrorHandlingViewModel<ViewState, ViewAction>

    private val context: Context get() = binding.root.context

    fun onViewCreated() = observeEvents()

    private fun observeEvents() = with(viewModel) {
        val lifecycleOwner = binding.lifecycleOwner ?: return@with

        networkErrorEvent.observe(lifecycleOwner) {
            context.showWarningSnackbar(binding.root, R.string.network_error_text)
        }

        genericErrorEvent.observe(lifecycleOwner) {
            context.showWarningSnackbar(binding.root, R.string.generic_error_text)
        }
    }
}
