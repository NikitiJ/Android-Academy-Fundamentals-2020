package dev.nikitij.android.myemptyapplication.extensions

import android.content.Context
import android.util.TypedValue
import kotlin.math.round

fun Context.convertDensityPixelsToPixels(dp: Int): Int {
    return round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            resources.displayMetrics)).toInt()
}