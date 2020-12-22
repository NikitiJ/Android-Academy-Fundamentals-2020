package dev.nikitij.android.myemptyapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.gridlayout.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Movie
import com.android.academy.fundamentals.homework.features.data.loadMovies
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.adapters.MoviesAdapter
import dev.nikitij.android.myemptyapplication.decorations.LinearVerticalSpacingItemDecoration
import dev.nikitij.android.myemptyapplication.extensions.convertDensityPixelsToPixels
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.MovieItemView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.Serializable

private const val ARG_MOVIES_LIST = "moviesList"

class MoviesListFragment : Fragment(), CoroutineScope by MainScope() {

    private var moviesList = listOf<Movie>()

    private var clickListener: OnCardItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moviesList = it.getSerializable(ARG_MOVIES_LIST) as List<Movie>
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCardItemClickListener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //добавить список фильмов
        val moviesContainer = getView()!!.findViewById<RecyclerView>(R.id.moviesListContainer)
        moviesContainer.adapter = MoviesAdapter()
        moviesContainer.layoutManager = GridLayoutManager(context, 2)
        moviesContainer.addItemDecoration(LinearVerticalSpacingItemDecoration(context!!.convertDensityPixelsToPixels(8)))

        launch {
            (moviesContainer.adapter as MoviesAdapter).submitList(loadMovies(activity!!.baseContext))
        }
    }

    interface OnCardItemClickListener {
        fun onMovieCardItemClick(model: Movie)
    }

    companion object {
        @JvmStatic
        fun newInstance(moviesListParams: List<MovieModel>) =
                MoviesListFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_MOVIES_LIST, moviesListParams as Serializable)
                    }
                }
    }
}