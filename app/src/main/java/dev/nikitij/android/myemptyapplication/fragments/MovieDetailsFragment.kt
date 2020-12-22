package dev.nikitij.android.myemptyapplication.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.gridlayout.widget.GridLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Movie
import com.bumptech.glide.Glide
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.adapters.ActorsAdapter
import dev.nikitij.android.myemptyapplication.decorations.LinearHorizontalSpacingItemDecoration
import dev.nikitij.android.myemptyapplication.extensions.convertDensityPixelsToPixels
import dev.nikitij.android.myemptyapplication.models.ActorModel
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.ActorItemView
import dev.nikitij.android.myemptyapplication.views.RatingBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

private const val ARG_MOVIE_MODEL = "movieModel"


class MovieDetailsFragment : Fragment(), CoroutineScope by MainScope() {

    private var movieModel: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieModel = it.getSerializable(ARG_MOVIE_MODEL) as Movie?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textViewBack).setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val logoBg = view.findViewById<ImageView>(R.id.logoTop)

        Glide.with(logoBg)
            .load(movieModel!!.backdrop)
            .into(logoBg)

        view.findViewById<TextView>(R.id.textViewAgeFromValue).text = "${movieModel!!.minimumAge}+"
        view.findViewById<TextView>(R.id.movieTitle).text = movieModel!!.title

        val strBuilder = StringBuilder(movieModel!!.genres.size)
        movieModel!!.genres.forEachIndexed { index, genre ->
            if (index == 0) {
                strBuilder.append(genre.name)
            } else {
                strBuilder.append(", ${genre.name}")
            }
        }
        view.findViewById<TextView>(R.id.genresListValue).text = strBuilder.toString()

        view.findViewById<RatingBarView>(R.id.ratingBar).setupStars(movieModel!!.getFiveStarRatingValue())

        view.findViewById<TextView>(R.id.reviewsCounterValue).text = "${movieModel!!.numberOfRatings} ${resources.getQuantityString(R.plurals.reviews_plurals, movieModel!!.numberOfRatings)}"
        view.findViewById<TextView>(R.id.storyLineText).text = movieModel!!.overview

        val actorsContainer = view.findViewById<RecyclerView>(R.id.actorsContainer)
        actorsContainer.adapter = ActorsAdapter()
        actorsContainer.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        actorsContainer.addItemDecoration(LinearHorizontalSpacingItemDecoration(context!!.convertDensityPixelsToPixels(16)))

        //загрузка актеров?
        //(actorsContainer.adapter as ActorsAdapter).submitList(movieModel!!.actors)
        launch {
            (actorsContainer.adapter as ActorsAdapter).submitList(movieModel!!.actors)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(model: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE_MODEL, model)
                }
            }
    }
}