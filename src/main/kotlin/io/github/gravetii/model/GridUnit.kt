package io.github.gravetii.model

import javafx.scene.image.Image

data class GridUnit(val alphabet: Alphabet, val point: GridPoint) {

    val letter: String = alphabet.get()
    val score: Int = alphabet.score
    val weight: Int = alphabet.weight

    val image: Image by lazy {
        val imgPath = "/images/${alphabet.get()}.png"
        Image(javaClass.getResource(imgPath)!!.toExternalForm())
    }

}