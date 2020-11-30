package dev.nikitij.android.myemptyapplication.views

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.models.ActorModel

class ActorItemView : FrameLayout {

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.layout_actor_card_view, this, true)
    }

    fun bind(model: ActorModel) {
        this.findViewById<TextView>(R.id.fullName).text = model.fullName
        this.findViewById<ImageView>(R.id.imageViewAvatar).setImageDrawable(context.getDrawable(model.drawableBgResource!!))
    }
}