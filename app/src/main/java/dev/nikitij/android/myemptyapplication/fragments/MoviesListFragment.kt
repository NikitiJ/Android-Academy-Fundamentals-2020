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
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.adapters.MoviesAdapter
import dev.nikitij.android.myemptyapplication.decorations.LinearVerticalSpacingItemDecoration
import dev.nikitij.android.myemptyapplication.extensions.convertDensityPixelsToPixels
import dev.nikitij.android.myemptyapplication.models.MovieModel
import dev.nikitij.android.myemptyapplication.views.MovieItemView
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_MOVIES_LIST = "moviesList"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var moviesList = listOf<MovieModel>()

    private var clickListener: OnCardItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moviesList = it.getSerializable(ARG_MOVIES_LIST) as List<MovieModel>
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //добавить список фильмов
        val moviesContainer = getView()!!.findViewById<RecyclerView>(R.id.moviesListContainer)
        moviesContainer.adapter = MoviesAdapter()
        moviesContainer.layoutManager = GridLayoutManager(context, 2)
        moviesContainer.addItemDecoration(LinearVerticalSpacingItemDecoration(context!!.convertDensityPixelsToPixels(8)))
        (moviesContainer.adapter as MoviesAdapter).submitList(moviesList)

        /*moviesList.forEach { movieModel ->
            val lp = android.widget.GridLayout.LayoutParams(ViewGroup.MarginLayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT))
            lp.setMargins(0, context!!.convertDensityPixelsToPixels(8),
                    context!!.convertDensityPixelsToPixels(8), context!!.convertDensityPixelsToPixels(8))
            lp.setGravity(Gravity.CENTER)

            val movieView = MovieItemView(context!!)
            movieView.bind(movieModel)
            movieView.layoutParams = lp
            moviesContainer.addView(movieView)

            movieView.findViewById<CardView>(R.id.mainWrapper).setOnClickListener {
                clickListener?.onMovieCardItemClick(movieModel)
            }
        }*/
    }

    //прокинуть клик на вьюшке фильма - на активность и запустить другой фрагмент и передать в него модель фильма!
    interface OnCardItemClickListener {
        fun onMovieCardItemClick(model: MovieModel)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(moviesListParams: List<MovieModel>) =
                MoviesListFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_MOVIES_LIST, moviesListParams as Serializable)
                    }
                }
    }
}