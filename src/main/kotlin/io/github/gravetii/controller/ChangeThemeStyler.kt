package io.github.gravetii.controller

import javafx.scene.image.ImageView

object ChangeThemeStyler {

    private var current: ImageView? = null

    fun applySelectStyle(imgView: ImageView) {
        current?.parent?.styleClass?.remove("theme-img-view-click")
        current = imgView
        current?.parent?.styleClass?.add("theme-img-view-click")
    }

}