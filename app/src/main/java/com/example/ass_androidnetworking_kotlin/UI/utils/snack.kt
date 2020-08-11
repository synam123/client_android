package com.fpoly.assignemnt_gd1.utils

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(
    @StringRes messageRes: Int,
    length: SnackBarLength = SnackBarLength.SHORT,
    crossinline f: Snackbar.() -> Unit = {}
) = snack(resources.getString(messageRes), length, f)

inline fun View.snack(
    message: String,
    length: SnackBarLength = SnackBarLength.SHORT,
    crossinline f: Snackbar.() -> Unit = {}
) = Snackbar.make(this, message, length.rawValue).apply {
    f()
    show()
}

enum class SnackBarLength(val rawValue: Int) {
    SHORT(Snackbar.LENGTH_SHORT),

    LONG(Snackbar.LENGTH_LONG),

    INDEFINITE(Snackbar.LENGTH_INDEFINITE);
}
