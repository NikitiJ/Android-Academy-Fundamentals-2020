package dev.nikitij.android.myemptyapplication.models

import java.io.Serializable

class MovieModel: Serializable {
    var ageFrom: Int = 13
    var title: String = ""
    var isFavorite: Boolean = false
    var rating: Int = 0
    var reviewsCounter: Int = 0
    var genresList: String = ""
    var duration: Int = 0
    var drawableBgResource: Int? = null
    var storyLine: String = ""
    var actors: List<ActorModel> = listOf()

    constructor(
        ageFrom: Int,
        title: String,
        isFavorite: Boolean,
        rating: Int,
        reviewsCounter: Int,
        genresList: String,
        duration: Int,
        drawableBgResource: Int?,
        storyLine: String,
        actors: List<ActorModel>
    ) {
        this.ageFrom = ageFrom
        this.title = title
        this.isFavorite = isFavorite
        this.rating = rating
        this.reviewsCounter = reviewsCounter
        this.genresList = genresList
        this.duration = duration
        this.drawableBgResource = drawableBgResource
        this.storyLine = storyLine
        this.actors = actors
    }
}