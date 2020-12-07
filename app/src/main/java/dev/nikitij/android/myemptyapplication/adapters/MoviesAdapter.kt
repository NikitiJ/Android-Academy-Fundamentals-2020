package dev.nikitij.android.myemptyapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.fragments.MovieDetailsFragment
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.RatingBarView

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var moviesList = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_view, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun submitList(newList: List<MovieModel>) {
        moviesList = newList.toMutableList()
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mainWrapper: CardView = itemView.findViewById(R.id.mainWrapper)
    private val logoImage: ImageView = itemView.findViewById(R.id.logoImage)
    private val ageFromValue: TextView = itemView.findViewById(R.id.textViewAgeFromValue)
    private val likeIcon: ImageView = itemView.findViewById(R.id.likeIcon)
    private val genresValueText: TextView = itemView.findViewById(R.id.genresListValue)
    private val ratingBarView: RatingBarView = itemView.findViewById(R.id.ratingBar)
    private val reviewsCounterValue: TextView = itemView.findViewById(R.id.reviewsCounterValue)
    private val movieTitleValue: TextView = itemView.findViewById(R.id.textViewMovieTitle)
    private val durationValue: TextView = itemView.findViewById(R.id.durationValue)

    fun bind(model: MovieModel) {
        model.drawableBgResource?.let { logoImage.setImageResource(it) }
        ageFromValue.text = "${model.ageFrom}+"
        if (model.isFavorite) {
            likeIcon.setImageResource(R.drawable.like_active)
        } else {
            likeIcon.setImageResource(R.drawable.ike_default)
        }
        genresValueText.text = model.genresList
        ratingBarView.setupStars(model.rating)
        reviewsCounterValue.text = "${model.reviewsCounter} ${itemView.context.resources.getQuantityString(R.plurals.reviews_plurals, model.reviewsCounter)}"
        movieTitleValue.text = model.title
        durationValue.text = "${model.duration} MIN"

        mainWrapper.setOnClickListener {
            (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, MovieDetailsFragment.newInstance(model))
                .addToBackStack(null)
                .commit()

        }
    }
}