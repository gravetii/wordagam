package io.github.gravetii.controller

import javafx.scene.image.ImageView

class ChangeThemeStyler {

    private var current: ImageView? = null

    private fun apply() = current?.parent?.styleClass?.add("theme-img-view-click")

    private fun remove() = current?.parent?.styleClass?.remove("theme-img-view-click")

    fun applySelectStyle(imgView: ImageView) {
        if (current != null) remove()
        current = imgView
        apply()
    }

}