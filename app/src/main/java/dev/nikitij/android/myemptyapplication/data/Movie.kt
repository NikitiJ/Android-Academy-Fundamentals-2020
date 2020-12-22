package com.android.academy.fundamentals.homework.features.data

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
) : Serializable {

    fun getFiveStarRatingValue(): Int {
        when(this.ratings) {
            in 0.0..2.0 -> return 1
            in 2.1..4.0 -> return 2
            in 4.1..6.0 -> return 3
            in 6.1..8.9 -> return 4
            in 9.1..10.0 -> return 5
        }
        return 1
    }
}

