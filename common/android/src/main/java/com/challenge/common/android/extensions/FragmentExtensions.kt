package com.challenge.common.android.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.challenge.common.android.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showWarningSnackbar(anchorView: View, @StringRes actionTextRes: Int) {
    val context = context ?: return
    showSnackbar(
        anchorView,
        resources.getString(actionTextRes),
        ContextCompat.getDrawable(context, R.drawable.shape_warning_snackbar_background)
    )
}

private fun showSnackbar(anchorView: View, actionText: String, background: Drawable?) =
    Snackbar
        .make(anchorView, actionText, Snackbar.LENGTH_LONG)
        .apply {
            setActionTextColor(ContextCompat.getColor(context, R.color.black))
            view.background = background
            show()
        }