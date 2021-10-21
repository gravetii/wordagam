package io.github.gravetii.model

import io.github.gravetii.util.Alphabet
import javafx.scene.image.Image

data class GridUnit(val alphabet: Alphabet, val point: GridPoint) {

    private val imgPath = "/images/${alphabet.get()}.png"
    val image: Image = Image(javaClass.getResource(imgPath)!!.toExternalForm())
    val letter: String = alphabet.get()
    val score: Int = alphabet.score
    val weight: Int = alphabet.weight

}