package com.challenge.common.android.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.challenge.common.android.R
import com.google.android.material.snackbar.Snackbar

fun Context.showWarningSnackbar(anchorView: View, @StringRes actionTextRes: Int) =
    showSnackbar(
        anchorView,
        resources.getString(actionTextRes),
        ContextCompat.getDrawable(this, R.drawable.shape_warning_snackbar_background)
    )

private fun showSnackbar(anchorView: View, actionText: String, background: Drawable?) =
    Snackbar
        .make(anchorView, actionText, Snackbar.LENGTH_LONG)
        .apply {
            setActionTextColor(ContextCompat.getColor(context, R.color.black))
            view.background = background
            show()
        }