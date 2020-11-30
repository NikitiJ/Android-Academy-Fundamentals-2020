package dev.nikitij.android.myemptyapplication.views

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.models.MovieModel

class MovieItemView : FrameLayout {

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.layout_movie_view, this, true)
    }

    fun bind(model: MovieModel) {
        this.findViewById<ImageView>(R.id.logoImage).setImageResource(model.drawableBgResource!!)
        this.findViewById<TextView>(R.id.textViewAgeFromValue).text = "${model.ageFrom}+"
        if (model.isFavorite) {
            this.findViewById<ImageView>(R.id.likeIcon).setImageDrawable(context.getDrawable(R.drawable.like_active))
        }

        this.findViewById<TextView>(R.id.genresListValue).text = model.genresList
        this.findViewById<RatingBarView>(R.id.ratingBar).setupStars(model.rating)

        this.findViewById<TextView>(R.id.reviewsCounterValue).text = "${model.reviewsCounter} ${resources.getQuantityString(R.plurals.reviews_plurals, model.reviewsCounter)}"
        this.findViewById<TextView>(R.id.textViewMovieTitle).text = model.title
        this.findViewById<TextView>(R.id.durationValue).text = "${model.duration} MIN"
    }
}