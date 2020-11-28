package dev.nikitij.android.myemptyapplication.models

import java.io.Serializable

class ActorModel : Serializable {
    var drawableBgResource: Int? = null
    var fullName: String = ""

    constructor(drawableBgResource: Int?, fullName: String) {
        this.drawableBgResource = drawableBgResource
        this.fullName = fullName
    }
}