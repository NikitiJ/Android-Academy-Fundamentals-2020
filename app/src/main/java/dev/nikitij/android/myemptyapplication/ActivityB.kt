package dev.nikitij.android.myemptyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
    }

    fun onBackClick(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun onForwardClick(view: View) {
        startActivity(Intent(this, ActivityC::class.java))
    }
}