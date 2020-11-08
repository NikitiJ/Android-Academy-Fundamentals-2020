package dev.nikitij.android.myemptyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.nikitij.android.myemptyapplication.fragments.FragmentA

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentsContainer, FragmentA())
            .commit()
    }
}