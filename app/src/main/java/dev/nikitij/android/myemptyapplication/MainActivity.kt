package dev.nikitij.android.myemptyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.nikitij.android.myemptyapplication.fragments.MovieDetailsFragment
import dev.nikitij.android.myemptyapplication.fragments.MoviesListFragment
import dev.nikitij.android.myemptyapplication.models.ActorModel
import dev.nikitij.android.myemptyapplication.models.MovieModel

class MainActivity : AppCompatActivity(), MoviesListFragment.OnCardItemClickListener {

    private var moviesList = listOf<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovieList()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer, MoviesListFragment.newInstance(moviesList))
            .commit()
    }

    private fun initMovieList() {
        val castActorsArray = listOf(
                ActorModel(R.drawable.robert_dw_jr_avatar, "Robert Downey Jr."),
                ActorModel(R.drawable.chris_ewans_avatar, "Chris Evans"),
                ActorModel(R.drawable.mark_ruffalo_avatar, "Mark Ruffalo"),
                ActorModel(R.drawable.chris_hems_avatar, "Chris Hemsworth")
        )
        moviesList = listOf(
                MovieModel(13, "Avengers: End Game", false, 4, 121, "Action, Drama", 121, R.drawable.avengers_logo,
                        getString(R.string.lorem_ipsum), castActorsArray),
                MovieModel(16, "Tenet", true, 5, 98, "Action, Drama, Sci-FI", 160, R.drawable.tenet_logo,
                        getString(R.string.lorem_ipsum), castActorsArray),
                MovieModel(13, "Black Widow", false, 4, 38, "Action, Drama", 98, R.drawable.black_widow_logo,
                        getString(R.string.lorem_ipsum), castActorsArray),
                MovieModel(13, "Wonder Woman 1984", false, 4, 78, "Action, Drama", 120, R.drawable.wonder_women_logo,
                        getString(R.string.lorem_ipsum), castActorsArray)
        )
    }

    override fun onMovieCardItemClick(model: MovieModel) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, MovieDetailsFragment.newInstance(model))
                .addToBackStack(null)
                .commit()
    }
}