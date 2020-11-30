package dev.nikitij.android.myemptyapplication.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import dev.nikitij.android.myemptyapplication.R

class RatingBarView : FrameLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_rating_bar_view, this, true)
    }

    fun setupStars(count: Int = 0) {
        when (count) {
            1 -> {
                this.findViewById<ImageView>(R.id.star_1).setImageDrawable(context.getDrawable(R.drawable.star_active))
            }
            2 -> {
                this.findViewById<ImageView>(R.id.star_1).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_2).setImageDrawable(context.getDrawable(R.drawable.star_active))
            }
            3 -> {
                this.findViewById<ImageView>(R.id.star_1).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_2).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_3).setImageDrawable(context.getDrawable(R.drawable.star_active))
            }
            4 -> {
                this.findViewById<ImageView>(R.id.star_1).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_2).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_3).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_4).setImageDrawable(context.getDrawable(R.drawable.star_active))
            }
            5 -> {
                this.findViewById<ImageView>(R.id.star_1).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_2).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_3).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_4).setImageDrawable(context.getDrawable(R.drawable.star_active))
                this.findViewById<ImageView>(R.id.star_5).setImageDrawable(context.getDrawable(R.drawable.star_active))
            }
        }
    }
}