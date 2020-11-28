package dev.nikitij.android.myemptyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.nikitij.android.myemptyapplication.fragments.MovieDetailsFragment
import dev.nikitij.android.myemptyapplication.fragments.MoviesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer, MoviesListFragment())
            .commit()
    }
}