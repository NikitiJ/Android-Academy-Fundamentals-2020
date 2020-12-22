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
import com.android.academy.fundamentals.homework.features.data.Movie
import com.bumptech.glide.Glide
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.fragments.MovieDetailsFragment
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.RatingBarView
import kotlin.random.Random

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var moviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_view, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun submitList(newList: List<Movie>) {
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

    fun bind(model: Movie) {
        Glide.with(logoImage)
            .load(model.backdrop)
            .into(logoImage)

        ageFromValue.text = "${model.minimumAge}+"
        if (Random.nextInt(1, 10) % 2 == 0) {
            likeIcon.setImageResource(R.drawable.like_active)
        } else {
            likeIcon.setImageResource(R.drawable.ike_default)
        }

        val strBuilder = StringBuilder(model.genres.size)
        model.genres.forEachIndexed { index, genre ->
            if (index == 0) {
                strBuilder.append(genre.name)
            } else {
                strBuilder.append(", ${genre.name}")
            }
        }
        genresValueText.text = strBuilder.toString()

        ratingBarView.setupStars(model.getFiveStarRatingValue())

        reviewsCounterValue.text = "${model.numberOfRatings}"
        movieTitleValue.text = model.title
        durationValue.text = "${model.runtime} MIN"

        mainWrapper.setOnClickListener {
            (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, MovieDetailsFragment.newInstance(model))
                .addToBackStack(null)
                .commit()
        }
    }
}