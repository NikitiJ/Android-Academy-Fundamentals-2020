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
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.adapters.ActorsAdapter
import dev.nikitij.android.myemptyapplication.decorations.LinearHorizontalSpacingItemDecoration
import dev.nikitij.android.myemptyapplication.extensions.convertDensityPixelsToPixels
import dev.nikitij.android.myemptyapplication.models.ActorModel
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.ActorItemView
import dev.nikitij.android.myemptyapplication.views.RatingBarView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_MOVIE_MODEL = "movieModel"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var movieModel: MovieModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieModel = it.getSerializable(ARG_MOVIE_MODEL) as MovieModel?
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

        view.findViewById<ImageView>(R.id.logoTop).setImageDrawable(context!!.getDrawable(movieModel!!.drawableBgResource!!))
        view.findViewById<TextView>(R.id.textViewAgeFromValue).text = "${movieModel!!.ageFrom}+"
        view.findViewById<TextView>(R.id.movieTitle).text = movieModel!!.title
        view.findViewById<TextView>(R.id.genresListValue).text = movieModel!!.genresList
        view.findViewById<RatingBarView>(R.id.ratingBar).setupStars(movieModel!!.rating)

        view.findViewById<TextView>(R.id.reviewsCounterValue).text = "${movieModel!!.reviewsCounter} ${resources.getQuantityString(R.plurals.reviews_plurals, movieModel!!.reviewsCounter)}"
        view.findViewById<TextView>(R.id.storyLineText).text = movieModel!!.storyLine

        val actorsContainer = view.findViewById<RecyclerView>(R.id.actorsContainer)
        actorsContainer.adapter = ActorsAdapter()
        actorsContainer.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        actorsContainer.addItemDecoration(LinearHorizontalSpacingItemDecoration(context!!.convertDensityPixelsToPixels(16)))
        (actorsContainer.adapter as ActorsAdapter).submitList(movieModel!!.actors)

        /*movieModel!!.actors.forEach {
            val lp = android.widget.GridLayout.LayoutParams(ViewGroup.MarginLayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT))
            lp.setMargins(0, context!!.convertDensityPixelsToPixels(8),
                    context!!.convertDensityPixelsToPixels(8), context!!.convertDensityPixelsToPixels(8))
            lp.setGravity(Gravity.CENTER)

            val actorView = ActorItemView(context!!)
            actorView.bind(it)
            actorView.layoutParams = lp
            actorsContainer.addView(actorView)
        }*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(model: MovieModel) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE_MODEL, model)
                }
            }
    }
}