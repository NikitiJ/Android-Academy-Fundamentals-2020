package dev.nikitij.android.myemptyapplication.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearVerticalSpacingItemDecoration : RecyclerView.ItemDecoration {
    private var spacing: Int = 5

    constructor(spacing: Int) : super() {
        this.spacing = spacing
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = spacing
    }
}